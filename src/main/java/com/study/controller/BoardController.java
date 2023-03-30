package com.study.controller;

import com.study.dto.BoardRequest;
import com.study.dto.SportResponse;
import com.study.service.BoardService;
import com.study.service.ImgService;
import com.study.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ImgService imgService;
    private final SportService sportService;

    @GetMapping("/boards/list")
    public String findBoardList(Model model) {
        return "boardlist";
    }

    @GetMapping("/boards")
    public String BoardAddForm(Model model) {
        List<SportResponse> getSports = sportService.findSports();
        model.addAttribute("getSports", getSports);

        return "boardadd";
    }

    @PostMapping("/boards")
    public String createBoard(BoardRequest boardRequest) throws Exception {
        Long boardNo = boardService.createBoard(boardRequest);
        imgService.updateBoardNo(boardNo);
        return "redirect:/boards/list";
    }
}
