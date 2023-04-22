package com.study.service;

import com.study.dto.BoardRequest;
import com.study.dto.BoardResponse;
import com.study.dto.PagingListGroup;
import com.study.entity.Board;
import com.study.entity.BoardRepository;
import com.study.entity.User;
import com.study.entity.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Mock
    private UserRepository userRepository;

    @BeforeAll
    public void createBoardEntity() throws ParseException {
        BoardRequest stubBoardReqeust=createBoardRequest();
        boardRepository.save(stubBoardReqeust.toTestCreateBoard());
    }
    @Test
    @DisplayName("게시글 생성")
    void createBoard() throws Exception {
        //given
        BoardRequest stubBoardReqeust=createBoardRequest();
        User stubUser=User.builder().email("테스트ID").no(1L).name("KIM").build();
        //stub
        when(boardRepository.save(any())).thenReturn(stubBoardReqeust.toTestCreateBoard());
        when(userRepository.findByEmail(stubBoardReqeust.getEmail())).thenReturn(Optional.ofNullable(stubUser));
        //when
        Long getBoardNo=boardService.createBoard(stubBoardReqeust);
        //then
        assertThat(getBoardNo).isEqualTo(stubBoardReqeust.getBoardNo());
        assertThat(getBoardNo).isNotNull();

    }

    @Test
    @DisplayName("게시글리스트 boardNo로 정렬 조회")
    void findAllBoards() {
        //given
        //stub
        List<Board> stubBoardList = createBoardList();
        PageRequest page=PageRequest.of(0,15,Sort.by(Sort.Direction.ASC,"boardNo"));
        when(boardRepository.findAll()).thenReturn(stubBoardList);
        //when
        PagingListGroup getBoardList=boardService.findAllBoards(page);
        //then
        assertThat(getBoardList).isNotNull();
    }

    @Test
    @DisplayName("게시글 상세보기")
    void findBoard() {
        //given

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
        boardRequest.setBoardNo(1L);
        boardRequest.setEmail("테스트ID");
        boardRequest.setBoardContent("asdasdas");
        boardRequest.setBoardName("제목이요");
        boardRequest.setBoardMapName("만남의광장");
        boardRequest.setBoardMapCordx("33.450701");
        boardRequest.setBoardMapCordy("126.570667");
        boardRequest.setBoardPromiseFrom("2014-04-01T01:00");
        boardRequest.setBoardPromiseUntil("2014-04-01T03:00");
        boardRequest.setSpoNo(1L);
        return boardRequest;
    }
}