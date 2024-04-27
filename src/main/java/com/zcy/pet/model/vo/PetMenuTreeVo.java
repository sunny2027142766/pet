package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PetMenuTreeVo {
    private Long mid;
    private String title;
    private String path;
    private String icon;
    private Integer status;
    private Long pid;
    private Integer sort;
    private Integer level;
    private List<PetMenuPageVo> children;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
