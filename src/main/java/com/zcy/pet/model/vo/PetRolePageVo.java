package com.zcy.pet.model.vo;

import lombok.Data;

/**
 * 角色分页vo视图
 */
@Data
public class PetRolePageVo {
    private Long rid;
    private String roleName;
    private String roleRemark;
    private String desc;
    private Integer isValid;
}
