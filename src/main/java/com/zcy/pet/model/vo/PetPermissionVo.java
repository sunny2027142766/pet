package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class PetPermissionVo {
    private Long pid;
    private String permName;
    private String permCode;
    private String description;
    private Integer status;
    private String mids; // 菜单ids
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
