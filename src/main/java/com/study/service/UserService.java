package com.study.service;

import com.study.dto.UserRequest;
import com.study.entity.User;
import com.study.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;



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






}
