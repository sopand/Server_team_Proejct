package com.study.service;

import com.study.dto.UserRequest;
import com.study.dto.UserResponse;
import com.study.entity.User;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import com.study.oAuth.UserAdapter;
import com.study.entity.UserRepository;
import com.study.oAuth.oAuthChk;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email)  {
        User user=userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EMAIL));
        return new UserAdapter(user);
    }
    @Transactional
    public Long createUser(UserRequest userRequest) throws Exception {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.CREATE_EMAIL_DUPLICATION);
        }
        PasswordCheck(userRequest);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = userRepository.save(userRequest.toCreateUserEntity());
        return user.getNo();
    }
    @Transactional(readOnly = true)
    public UserResponse findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserResponse::new).orElse(null);
    }

    @Transactional
    public void modifyUser(UserRequest userRequest) throws Exception {
        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EMAIL));
        if (user.getOauthMemberCheck().equals(oAuthChk.OAUTH_USER.getStatus())) {
            user.oAuthUserUpdate(userRequest);
            return ;
        }
        if (!passwordEncoder.matches(userRequest.getBeforePassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.BEFORE_PASSWORD_UN_PASSWORD);
        }
        PasswordCheck(userRequest);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.NormalUserUpdate(userRequest);
    }

    public static void PasswordCheck(UserRequest userRequest) throws Exception {
        if (!userRequest.getPassword().equals(userRequest.getPasswordCheck())) {
            throw new CustomException(ErrorCode.PASSWORD_UN_PASSWORD_CHECK);
        }
    }


}

