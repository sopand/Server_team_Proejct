package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Club;
import com.study.entity.User;
import lombok.Data;

@Data
public class ClubRequest {
        private String clubEmail;
        private Long boardNo;

        public Club toCreateClubEntity(){
            Board setBoardByBoardNo=Board.builder().boardNo(boardNo).build();
            return Club.builder().board(setBoardByBoardNo).clubEmail(clubEmail).build();
        }
}
