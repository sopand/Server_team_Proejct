package com.study.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<Img,Long> {

    List<Img> findByBoard_BoardNo(Long boardNo);


}
