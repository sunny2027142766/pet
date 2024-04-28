package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.zcy.pet.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PetMenu extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long mid; // 菜单ID
    private String title; // 菜单标题
    private String path; // 菜单路径
    private Long icon; // 菜单图标ID
    private Long pid; // 菜单的父ID
    private Integer level; // 菜单的层级
    private Integer sort; // 菜单的排序
    private Integer isFront; //  是否前台菜单(0:否;1:是)
    private Integer status; //  状态(0:禁用;1:启用)
    /**
     * 逻辑删除标识(0:未删除;1:已删除)
     */
    @TableLogic(delval = "1", value = "0")
    private Integer deleted;
}
