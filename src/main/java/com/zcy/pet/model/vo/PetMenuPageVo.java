package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 菜单分页vo视图
 */
@Data
public class PetMenuPageVo {
    private Long mid;
    private String title;
    private String path;
    private Integer icon;
    private String iconPath;
    private Long pid;
    private Integer sort;
    private Integer level;
    private Integer isFront;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
