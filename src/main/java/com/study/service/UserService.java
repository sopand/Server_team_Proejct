package com.study.service;

import com.study.dto.UserRequest;
import com.study.entity.User;
import com.study.entity.UserRepository;
import com.study.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

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

    public String login(UserRequest userRequest){
        User user=userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 Email 입니다"));
        if(!passwordEncoder.matches(userRequest.getPassword(),user.getPassword())){
            throw new IllegalStateException("잘못된 비밀번호 입니다");
        }
        List<String> roles=new ArrayList<>();
        roles.add(user.getRole().name());
        return jwtTokenProvider.createToken(user.getNickname(),roles);
    }
}
