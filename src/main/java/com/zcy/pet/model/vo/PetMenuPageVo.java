package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜单分页vo视图
 */
@Data
public class PetMenuPageVo {
    private Long mid;
    private String title;
    private String path;
    private String icon;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Long pid;
    private Integer sort;
    private Integer level;
    private List<PetMenuPageVo> children;
}
