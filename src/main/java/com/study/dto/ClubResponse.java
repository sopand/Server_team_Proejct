package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Club;
import com.study.entity.User;
import lombok.Getter;

@Getter
public class ClubResponse {

    private Long clubNo;
    private User user;
    private Board board;


    public ClubResponse(Club entity){
        this.clubNo=entity.getClubNo();
        this.user=entity.getUser();
        this.board=entity.getBoard();
    }
}
