package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetMenuVo {
    private Long mid; // 菜单ID
    private Long pid; // 菜单的父ID
    private String title; // 菜单标题
    private String path; // 菜单路径
    private String icon; // 图标URL
    private Integer level; // 菜单的层级
    private Integer sort; // 菜单的排序
    private Integer isFront; //  是否前台菜单(0:否;1:是)
}
