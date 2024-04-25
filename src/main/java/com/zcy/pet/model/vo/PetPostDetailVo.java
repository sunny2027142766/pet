package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetPostDetailVo {
    private Long pid;
    private String title;
    private String img;
    private String description;
    private String content;
    private Integer commentNum;
    private Integer likeNum;
    private Integer shareNum;
    private Long uid;
    private String username;
    private String avatar;
    private String createTime;
}
