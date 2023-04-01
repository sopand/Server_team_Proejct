package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Club;
import com.study.entity.User;
import lombok.Data;

@Data
public class ClubRequest {
        private Long no;
        private Long boardNo;

        public Club toCreateClubEntity(){
            Board setBoardByBoardNo=Board.builder().boardNo(boardNo).build();
            User setUserByUserNo=User.builder().no(no).build();
            return Club.builder().board(setBoardByBoardNo).user(setUserByUserNo).build();
        }
}
