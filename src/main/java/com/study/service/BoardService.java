package com.study.service;

import com.study.dto.BoardRequest;
import com.study.dto.BoardResponse;
import com.study.entity.BoardRepository;
import com.study.entity.User;
import com.study.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<BoardResponse> findAllBoards(){
        List<BoardResponse> getAllBoards=boardRepository.findAllByOrderByBoardNoAsc().stream()
                .filter(entity->entity!=null)
                .map(BoardResponse::new).toList();
        return getAllBoards;
    }
    
    @Transactional(readOnly = true)
    public BoardResponse findBoard(Long boardNo){
        return boardRepository.findByBoardNo(boardNo)
                .map(BoardResponse::new)
                .orElseThrow(()->new IllegalArgumentException("찾을수 없는 게시글입니다"));
    }

}
