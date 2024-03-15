package com.zcy.pet.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PetRoleVo {
    private Long rid;
    private String roleRemark;
    private String desc;
    private Integer isValid;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
