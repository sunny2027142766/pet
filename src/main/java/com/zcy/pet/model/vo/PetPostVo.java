package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetPostVo {
    private Long pid;
    private String title;
    private String desc;
    private Integer content;
    private String commentNum;
    private String likeNum;
    private String shareNum;
}
