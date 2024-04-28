package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *  菜单持久化对象
 */
@Data
public class PetMenuBo {
    private Long mid; // 菜单ID
    private Long pid; // 父菜单ID
    private Integer icon; // 图标ID
    private String title; // 菜单名称
    private String path; // 菜单路径
    private String iconPath; // 菜单图标路径
    private Integer isFront; // 是否前台菜单
    private Integer sort; // 排序
    private Integer level; // 菜单层级
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
