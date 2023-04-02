package com.study.service;

import com.study.dto.BoardRequest;
import com.study.dto.BoardResponse;
import com.study.entity.Board;
import com.study.entity.BoardRepository;
import com.study.entity.User;
import com.study.entity.UserRepository;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
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

    /**
     * 모집글을 등록하는 로직,
     * @param boardRequest = 사용자가 입력한 게시글에대한 데이터값을 가지고 있는 객체,
     * @return = 해당 로직이 정상 작동하였는지에 대한 검증을 하기 위한 Long값, 생성된 Board의 고유번호값이다.
     * @throws Exception =
     */
    @Transactional
    public Long createBoard(BoardRequest boardRequest) throws Exception {
        User getUser=userRepository.findByEmail(boardRequest.getEmail()).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER_EMAIL));
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
    
    @Transactional
    public BoardResponse findBoard(Long boardNo){
        Board getBoard=boardRepository.findByBoarAndClubList(boardNo).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));
        getBoard.UpdateBoardHit();
        return new BoardResponse(getBoard);
    }

}
