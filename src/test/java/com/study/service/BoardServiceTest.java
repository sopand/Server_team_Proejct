package com.study.service;

import com.study.dto.BoardRequest;
import com.study.entity.Board;
import com.study.entity.BoardRepository;
import com.study.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Transactional
class BoardServiceTest {
    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 생성")
    void createBoard() throws ParseException {
        //given
        BoardRequest getBoardReqeust=createBoardRequest();
        //stub
        when(boardRepository.save(any())).thenReturn(getBoardReqeust.toCreateBoard());
        //when
        Board getBoardEntity=boardRepository.save(getBoardReqeust.toCreateBoard());
        //then
        assertThat(getBoardReqeust.getBoardName()).isEqualTo(getBoardEntity.getBoardName());
        assertThat(getBoardReqeust.getBoardContent()).isEqualTo(getBoardEntity.getBoardContent());

    }

    @Test
    @DisplayName("게시글리스트 boardNo로 정렬 조회")
    void findAllBoards() {
        //given
        //stub
        List<Board> stubBoardList=createBoardList();
        when(boardRepository.findAllByOrderByBoardNoAsc()).thenReturn(stubBoardList);
        //when
        List<Board> getBoardList=boardRepository.findAllByOrderByBoardNoAsc();
        //then
        assertThat(getBoardList).isEqualTo(stubBoardList);
    }

    @Test
    void findBoard() {
    }

    private List<Board> createBoardList(){
        List<Board> stubBoardList=new ArrayList<>();
        stubBoardList.add(Board.builder().boardNo(1L).boardName("가나다").boardContent("테스트1호").build());
        stubBoardList.add(Board.builder().boardNo(2L).boardName("라마바").boardContent("테스트2호").build());
        stubBoardList.add(Board.builder().boardNo(3L).boardName("사아자").boardContent("테스트3호").build());
        return stubBoardList;
    }

    private BoardRequest createBoardRequest() {
        BoardRequest boardRequest = new BoardRequest();
        boardRequest.setBoardContent("asdasdas");
        boardRequest.setBoardName("제목이요");
        boardRequest.setBoardMapName("만남의광장");
        boardRequest.setBoardMapCordx("33.450701");
        boardRequest.setBoardMapCordy("126.570667");
        boardRequest.setBoardPromiseFrom("2014-04-01T01:00");
        boardRequest.setBoardPromiseUntil("2014-04-01T03:00");
        boardRequest.setUser(User.builder().no(1L).build());
        boardRequest.setSpoNo(1L);
        return boardRequest;
    }
}