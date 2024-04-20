package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  角色表持久化对象
 */
@Data
public class PetRoleBo {
    private Long rid;
    private String roleName;
    private String roleRemark;
    private String desc;
    private Integer isValid;
    private String createTime;
    private String updateTime;
}
