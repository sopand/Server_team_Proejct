package com.study.entity;

import com.study.config.DataBaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(DataBaseConfig.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImgRepositoryTest {

    @Autowired
    private ImgRepository imgRepository;

    @BeforeAll
    void createImg() {
        Board setBoard = Board.builder().boardNo(1L).build();
        Img setImg = Img.builder().board(setBoard).imgNew("TDDNEWNAME").imgDirectory("/TDD/TDDNEWNAME").imgOrigin("TDDORIGIN").imgDate(new Date()).build();
        imgRepository.save(setImg);
    }

    @Test
    @DisplayName("등록된 게시글의 고유번호로 이미지 찾아오기")
    void findByBoard_BoardNo() {
        //given
        Long boardNo = 1L;
        //when
        List<Img> getImgList=imgRepository.findByBoard_BoardNo(boardNo);
        //then
        assertThat(getImgList).isNotEmpty();
    }


}