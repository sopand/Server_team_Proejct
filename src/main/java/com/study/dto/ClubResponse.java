package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Club;
import com.study.entity.User;
import com.study.entity.UserRepository;
import lombok.Getter;

@Getter
public class ClubResponse {

    private Long clubNo;
    private String clubEmail;
    private BoardResponse board;


    public ClubResponse(Club entity){
        this.clubNo=entity.getClubNo();
        this.clubEmail=entity.getClubEmail();
        this.board=new BoardResponse(entity.getBoard());
    }
}
