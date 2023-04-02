package com.study.service;

import com.study.dto.BoardRequest;
import com.study.dto.BoardResponse;
import com.study.dto.PagingListGroup;
import com.study.entity.Board;
import com.study.entity.BoardRepository;
import com.study.entity.User;
import com.study.entity.UserRepository;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 모집글을 등록하는 로직,
     * @param boardRequest = 사용자가 입력한 게시글에대한 데이터값을 가지고 있는 객체,
     * @return = 해당 로직이 정상 작동하였는지에 대한 검증을 하기 위한 Long값, 생성된 Board의 고유번호값이다.
     * @throws Exception =
     */
    @Transactional
    public Long createBoard(BoardRequest boardRequest) throws Exception {
        User getUser=userRepository.findByEmail(boardRequest.getEmail()).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER_EMAIL));
        boardRequest.setUser(getUser);
        return boardRepository.save(boardRequest.toCreateBoard()).getBoardNo();
    }

    @Transactional(readOnly = true)
    public PagingListGroup findAllBoards(Pageable pageable){
      Page<Board> getPagingList=boardRepository.findAll(pageable);
        return setPagingData(getPagingList);
    }
    
    @Transactional
    public BoardResponse findBoard(Long boardNo){
        Board getBoard=boardRepository.findByBoarAndClubList(boardNo).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));
        getBoard.UpdateBoardHit();
        return new BoardResponse(getBoard);
    }


    public static PagingListGroup setPagingData(Page<Board> pagingBoardList) {
        List<BoardResponse> pagingBoardResponse = pagingBoardList.stream().filter(entity -> pagingBoardList != null).map(BoardResponse::new).toList();
        int nowPage = pagingBoardList.getPageable().getPageNumber() + 1; // 현재 페이지에 대한 값으로 pageable의 시작페이지가 0이기 때문에 +1 시켜 1부터 시작하게 만든다.
        int startPage = Math.max(nowPage - 4, 1); // View에 출력될 최소페이지설정, 최소 값은 1이고 Now(현재 페이지)값 - 4한값이 더 크다면 시작 페이지 값이 변경된다.
        int endPage = Math.min(nowPage + 5, pagingBoardList.getTotalPages());  // View에 보이게 될 최대 페이지 사이즈,
        PagingListGroup paging=PagingListGroup.builder()
                .list(pagingBoardResponse)
                .nowPage(nowPage)
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return paging;
    }
}
