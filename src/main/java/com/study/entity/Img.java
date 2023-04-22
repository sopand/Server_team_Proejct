package com.study.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;
    @Column(name = "img_origin")
    private String imgOrigin;

    @Column(name = "img_new")
    private String imgNew;
    @Column(name="img_directory")
    private String imgDirectory;


    @Column(name = "img_date")
    private Date imgDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;



    public void updateBoardNo(Long boardNo){
        this.board=Board.builder().boardNo(boardNo).build();
    }




}
