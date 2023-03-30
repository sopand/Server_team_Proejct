package com.study.service;


import com.study.entity.Img;
import com.study.entity.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImgService {
    private final ImgRepository imgRepository;
    @Value("${file.Upimg}")
    private String path;

    /**
     * 이미지를 등록해주는 로직,
     * @param file = 사용자가 입력한 이미지의 데이터가 들어있는 객체,
     * @return 
     * @throws IOException
     */
    @Transactional
    public ModelAndView createBoardImg(MultipartFile file) throws IOException {
        ModelAndView mv=new ModelAndView("jsonView");
        String imgOriginalName = file.getOriginalFilename(); // 입력한 원본 파일의 이름
        String uuid = String.valueOf(UUID.randomUUID()); // toString 보다는 valueOf를 추천 , NPE에러 예방,
        String extension = imgOriginalName.substring(imgOriginalName.lastIndexOf(".")); // 원본파일의 파일확장자
        String savedName = uuid + extension; // 랜덤이름 + 확장자
        File converFile = new File(path, savedName); // path = 상품 이미지 파일의 저장 경로가 들어있는 프로퍼티 설정값
        if (!converFile.exists()) {
            converFile.mkdirs();
        }
        file.transferTo(converFile);
        String imgView="/SportTeam/"+savedName;
        mv.addObject("uploaded",true);
        mv.addObject("url",imgView);
        Img img=Img.builder().imgOrigin(imgOriginalName).imgNew(savedName).imgDirectory(imgView).build();
        imgRepository.save(img);
        return mv;
    }

    @Transactional
    public void updateBoardNo(Long boardNo){
        imgRepository.findByBoard_BoardNo(boardNo).stream().filter(entity-> entity!=null).forEach(entity->entity.updateBoardNo(boardNo));
    }
}
