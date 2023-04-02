package com.study.entity;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {


    List<Board> findAllByOrderByBoardNoAsc();



    @Query(value ="SELECT bo FROM Board bo LEFT JOIN FETCH bo.club WHERE bo.boardNo = :boardNo")
    Optional<Board> findByBoarAndClubList(@Param("boardNo") Long boardNo);


}
