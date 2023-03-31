package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @ManyToOne
    @JoinColumn(name="no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spo_no")
    private Sport sport;


    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Img> img;

    private String boardName;
    private String boardContent;

    @Column(name = "board_promise_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date boardPromiseFrom;

    @Column(name = "board_promise_until")
    @Temporal(TemporalType.TIMESTAMP)
    private Date boardPromiseUntil;

    private String boardMapCordx;
    private String boardMapCordy;

    private String boardMapName;
    private String boardStatus;

    @Temporal(TemporalType.DATE)
    private Date createDate;











}
