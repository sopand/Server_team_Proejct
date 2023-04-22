package com.study.service;

import com.study.dto.BoardResponse;
import com.study.dto.PagingListGroup;
import com.study.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuplicationService {

    public static PagingListGroup setPagingData(Page<?> pagingListEntity,List<?> pagingResponseList) {
        int nowPage = pagingListEntity.getPageable().getPageNumber() + 1; // 현재 페이지에 대한 값으로 pageable의 시작페이지가 0이기 때문에 +1 시켜 1부터 시작하게 만든다.
        int startPage = Math.max(nowPage - 4, 1); // View에 출력될 최소페이지설정, 최소 값은 1이고 Now(현재 페이지)값 - 4한값이 더 크다면 시작 페이지 값이 변경된다.
        int endPage = Math.min(nowPage + 5, pagingListEntity.getTotalPages());  // View에 보이게 될 최대 페이지 사이즈,
        PagingListGroup paging=PagingListGroup.builder()
                .list(pagingResponseList)
                .nowPage(nowPage)
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return paging;
    }
}
