package com.study.entity;

import com.study.config.DataBaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(DataBaseConfig.class)
@ActiveProfiles("test")
class SportRepositoryTest {

    @Autowired
    private SportRepository sportRepository;
    @Test
    void 모든_Sport데이터_정렬로_가져오기() {
        //given
        //when
        List<Sport> getSportListAsc=sportRepository.findAllByOrderBySpoNoAsc();
        //then
        assertThat(getSportListAsc).isNotEmpty();

    }
}