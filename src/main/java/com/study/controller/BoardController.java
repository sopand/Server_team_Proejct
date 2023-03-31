package com.study.controller;

import com.study.dto.BoardRequest;
import com.study.dto.BoardResponse;
import com.study.dto.SportResponse;
import com.study.service.BoardService;
import com.study.service.ImgService;
import com.study.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ImgService imgService;
    private final SportService sportService;

    /**
     * 게시글 보기를 클릭할 시 작동하는 맵핑, 게시글의 리스트를 출력해줌
     * @param model = View에 출력할 데이터를 설정하기 위한 객체 ( 모든 게시글에대한 리스트를 출력 하기 위해 사용 )
     * @return = boardlist.html로 이동
     */
    @GetMapping("/boards/list")
    public String findBoardList(Model model) {
        List<BoardResponse> getAllBoards = boardService.findAllBoards();
        model.addAttribute("board", getAllBoards);
        return "boardlist";
    }

    /**
     * 게시글의 상세페이지로 이동시켜주는 맵핑,
     * @param model = View에 출력할 데이터를 설정하기 위한 객체 ( 게시글에 대한 정보를 담음 )
     * @param boardNo = 사용자가 클릭한 게시글의 고유 번호(PK)
     * @return = boarddetail.html로 이동
     */
    @GetMapping("/boards/detail/{boardNo}")
    public String findBoard(Model model, @PathVariable Long boardNo){
        BoardResponse getBoard=boardService.findBoard(boardNo);
        model.addAttribute("board",getBoard);
        return "boarddetail";
    }

    /**
     * 제품 생성 페이지로 이동시켜주는 맵핑
     * @param model = View에 출력할 데이터를 설정하기 위한 객체, ( SELECT박스의 항목을 출력해주기 위함 )
     * @return = boardadd.html 로이동
     */
    @GetMapping("/boards")
    public String BoardAddForm(Model model) {
        List<SportResponse> getSports = sportService.findSports();
        model.addAttribute("getSports", getSports);

        return "boardadd";
    }

    /**
     * 게시글 생성기능을 담당하는 맵핑.
     * @param boardRequest = 사용자가 게시글생성을 하기위해 입력한 데이터들을 받아오기 위한 객체,
     * @return = forward 이동시 새로고침할 경우 같은 데이터의 중복생성 가능성이 있기 때문에 redirect로 list페이지로 이동
     * @throws Exception = boardRequest의 String -> Date Parse를 하기위해 Exception처리,
     */

    @PostMapping("/boards")
    public String createBoard(BoardRequest boardRequest) throws Exception {
        Long boardNo = boardService.createBoard(boardRequest);
        imgService.updateBoardNo(boardNo);
        return "redirect:/boards/list";
    }

}
