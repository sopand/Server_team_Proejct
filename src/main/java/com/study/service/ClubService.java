package com.study.service;

import com.study.dto.BoardResponse;
import com.study.dto.ClubRequest;
import com.study.dto.ClubResponse;
import com.study.entity.Club;
import com.study.entity.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubResponse createClubUser(ClubRequest clubRequest){

        return new ClubResponse(clubRepository.save(clubRequest.toCreateClubEntity()));
    }
}
