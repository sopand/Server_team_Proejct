package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class PagingListGroup {

    private List<?> list;
    private int nowPage;
    private int startPage;
    private int endPage;


}
