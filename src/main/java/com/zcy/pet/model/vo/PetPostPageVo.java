package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PetPostPageVo {
    private Long pid;
    private String title;
    private String description;
    private String content;
    private Integer commentNum;
    private Integer likeNum;
    private Integer shareNum;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
