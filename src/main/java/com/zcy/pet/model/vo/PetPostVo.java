package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class PetPostVo {
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String createTime;
}
