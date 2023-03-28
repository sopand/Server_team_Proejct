package com.study.service;

import com.study.dto.UserRequest;
import com.study.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void createUser(UserRequest userRequest) throws ParseException {
        userRepository.save(userRequest.toCreateUserEntity());


    }
}
