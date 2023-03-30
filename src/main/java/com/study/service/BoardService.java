package com.study.service;

import com.study.dto.BoardRequest;
import com.study.entity.BoardRepository;
import com.study.entity.User;
import com.study.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createBoard(BoardRequest boardRequest) throws Exception {
        User getUser=userRepository.findByEmail(boardRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("찾는 유저가 없는 유저입니다."));
        boardRequest.setUser(getUser);
        return boardRepository.save(boardRequest.toCreateBoard()).getBoardNo();
    }
}
