package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PetTypePageVo {
    private Long tid;
    private String name;
    private String subName;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
