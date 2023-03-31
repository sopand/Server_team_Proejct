package com.study.entity;

import com.study.config.DataBaseConfig;
import com.study.dto.BoardRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(DataBaseConfig.class)
@ActiveProfiles("test")
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Test
    void 게시글목록을_ORDERBY_정렬해서가져오기 (){
        //given
        //when
        List<Board> getBoardList=boardRepository.findAllByOrderByBoardNoAsc();
        //then
        assertThat(getBoardList).isNotEmpty();

    }

    @Test
    void 게시글등록() throws ParseException {
        //given
        BoardRequest boardRequest = createBoardRequest();
        Board board = boardRequest.toCreateBoard();
        //when
        Board insertBoard = boardRepository.save(board);
        //then
        assertThat(insertBoard.getBoardName()).isEqualTo(board.getBoardName());
        assertThat(insertBoard.getBoardContent()).isEqualTo(board.getBoardContent());
    }

    @Test
    void 고유번호로_게시글찾기(){
        //given
        Long boardNo=1L;
        //when
        Board getBoard=boardRepository.findByBoardNo(boardNo).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없어요"));
        //then
        assertThat(getBoard.getBoardNo()).isEqualTo(boardNo);

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