package com.study.service;

import com.study.dto.UserRequest;
import com.study.dto.UserResponse;
import com.study.entity.User;
import com.study.entity.UserRepository;
import com.study.entity.oAuthChk;
import com.study.oAuth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> getUser = userRepository.findByEmail(email);
        if (getUser.isPresent() == false) {
            throw new UsernameNotFoundException("해당 이메일은 등록되어있지 않습니다.");
        }
        return new PrincipalDetails(getUser.get());


    }


    @Transactional
    public Long createUser(UserRequest userRequest) throws Exception {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일 입니다.");
        }
        PasswordCheck(userRequest);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = userRepository.save(userRequest.toCreateUserEntity());
        return user.getNo();
    }


    @Transactional(readOnly = true)
    public UserResponse findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserResponse::new).orElseThrow();
    }

    @Transactional
    public void modifyUser(UserRequest userRequest) throws Exception {
        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("해당하는 이메일이 없는걸요??"));
        PasswordCheck(userRequest);

        if (user.getOauthMemberCheck().equals(oAuthChk.OAUTH_USER.getStatus())) {
            user.updateUser(userRequest);
            return;
        }
        if (!passwordEncoder.matches(userRequest.getBeforePassword(), user.getPassword())) {
            throw new Exception("인증을 위한 기존 비밀번호가 일치하지 않습니다.");
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.updateUser(userRequest);
    }

    public static void PasswordCheck(UserRequest userRequest) throws Exception {
        if (!userRequest.getPassword().equals(userRequest.getPasswordCheck())) {
            throw new Exception("입력한 비밀번호와 비밀번호 확인 값이 틀립니다.");
        }
    }

}

