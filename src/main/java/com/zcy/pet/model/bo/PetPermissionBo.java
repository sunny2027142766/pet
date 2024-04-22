package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  角色表持久化对象
 */
@Data
public class PetPermissionBo {
    private Long pid;
    private String permissionName;
    private String description;
    private String createTime;
    private String updateTime;
}
