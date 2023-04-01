package com.study.service;

import com.study.dto.SportResponse;
import com.study.entity.Sport;
import com.study.entity.SportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class SportServiceTest {

    @InjectMocks
    private SportService sportService;

    @Mock
    private SportRepository sportRepository;
    @Test
    @DisplayName("모든 스포츠종목의 리스트를 가져오는 로직")
    void findSports() {
        //given
        //stub
        List<Sport> stubSportList=createSportList();
        when(sportRepository.findAllByOrderBySpoNoAsc()).thenReturn(stubSportList);
        //when
        List<SportResponse> getSportList =sportService.findSports();
        ///then
        assertThat(getSportList).isNotEmpty();
        assertThat(getSportList.get(0).getSpoName()).isEqualTo(stubSportList.get(0).getSpoName());


    }

    private List<Sport> createSportList(){
        List<Sport> stubSportList=new ArrayList<>();
        stubSportList.add(Sport.builder().spoNo(1L).spoName("축구").spoCategory("구기종목").build());
        stubSportList.add(Sport.builder().spoNo(2L).spoName("족구").spoCategory("구기종목").build());
        stubSportList.add(Sport.builder().spoNo(3L).spoName("배구").spoCategory("구기종목").build());
        return stubSportList;
        
    }
}