package com.study.service;

import com.study.dto.UserRequest;
import com.study.dto.UserResponse;
import com.study.entity.User;
import com.study.entity.UserRepository;
import com.study.oAuth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("이메일 "+email);
        Optional<User> getUser=userRepository.findByEmail(email);
        if(getUser.isPresent()==false){
            throw new UsernameNotFoundException("해당 이메일은 등록되어있지 않습니다.");
        }else{
            return new PrincipalDetails(getUser.get());
        }

    }



    public Long createUser(UserRequest userRequest) throws Exception {
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일 입니다.");
        }
        if(!userRequest.getPassword().equals(userRequest.getPasswordCheck())){
            throw new Exception("비밀번호가 일치하지 않아요");
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user=userRepository.save(userRequest.toCreateUserEntity());
        return user.getNo();
    }



    @Transactional(readOnly = true)
    public UserResponse findUserByEmail(String email){
        return userRepository.findByEmail(email).map(UserResponse::new).orElseThrow();
    }
}
