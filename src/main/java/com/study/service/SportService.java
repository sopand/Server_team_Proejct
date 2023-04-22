package com.study.service;

import com.study.dto.SportResponse;
import com.study.entity.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportService {

    private final SportRepository sportRepository;

    /**
     * 헤더에 각각의 운동 종목을 출력시켜주기 위한 로직
     * @return = Sport를 응답객체로 변환해서 리턴
     */
    public List<SportResponse> findSports(){
        return sportRepository.findAllByOrderBySpoNoAsc().stream().map(SportResponse::new).toList();
    }

}
