package com.study.dto;


import com.study.entity.Sport;
import lombok.Getter;

@Getter
public class SportResponse {
    private Long spoNo;
    private String spoName;
    private String spoCategory;

    public SportResponse(Sport sport){
        this.spoNo=sport.getSpoNo();
        this.spoName=sport.getSpoName();
        this.spoCategory=sport.getSpoCategory();
    }
}
