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

import static com.study.service.DuplicationService.setPagingData;

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

    /**
     * 모집글 리스트를 보여주기 위한 로직
     * @param pageable = 페이징 처리를 위한 기본 데이터가 설정되어있는 객체
     * @return = 페이징 데이터만으로는 VIew에서 페이지를 보여줄 수 없기 때문에 Paging데이터를 처리하는 메서드를 통해 Paging데이터를 가공,
     */
    @Transactional(readOnly = true)
    public PagingListGroup findAllBoards(Pageable pageable){
      Page<Board> getPagingList=boardRepository.findAll(pageable);
        List<BoardResponse> pagingBoardResponse = getPagingList.stream().filter(entity -> getPagingList != null).map(BoardResponse::new).toList();
        return setPagingData(getPagingList,pagingBoardResponse);
    }

    /**
     * 모집글의 상세보기 기능을 담당하는 로직
     * @param boardNo = 상세정보를 보려고 하는 게시글의 고유번호
     * @return = Board를 응답객체로 변환시켜 리턴
     */
    @Transactional
    public BoardResponse findBoard(Long boardNo){
        Board getBoard=boardRepository.findByBoarAndClubList(boardNo).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));
        getBoard.UpdateBoardHit();
        return new BoardResponse(getBoard);
    }



}
