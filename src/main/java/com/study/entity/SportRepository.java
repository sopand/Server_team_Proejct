package com.study.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport,Long> {

    List<Sport> findAllByOrderBySpoNoAsc();
}
