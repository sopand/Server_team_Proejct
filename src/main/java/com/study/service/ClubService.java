package com.study.service;

import com.study.dto.BoardResponse;
import com.study.dto.ClubRequest;
import com.study.dto.ClubResponse;
import com.study.entity.Club;
import com.study.entity.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    /**
     * 유저의 모집 참가를 하기위한 로직
     * @param clubRequest = 참가하려는 유저와 게시글의 데이터가 저장되어있는 객체
     * @return = save 성공 여부를 확인하기위한 리턴
     */
    @Transactional
    public ClubResponse createClubUser(ClubRequest clubRequest){
        return new ClubResponse(clubRepository.save(clubRequest.toCreateClubEntity()));
    }
}
