package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  角色表持久化对象
 */
@Data
public class PetMenuBo {
    private Long mid;
    private String title;
    private String path;
    private Integer icon;
    private String isValid;
    private String createTime;
    private String updateTime;
}
