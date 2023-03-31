package com.study.entity;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {


    List<Board> findAllByOrderByBoardNoAsc();
    Optional<Board> findByBoardNo(Long boardNo);


}
