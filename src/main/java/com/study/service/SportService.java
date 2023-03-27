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

    public List<SportResponse> findSports(){
        return sportRepository.findAll().stream().map(SportResponse::new).toList();
    }

}
