package com.zcy.pet.model.entity;

import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetPermission extends BaseEntity {
    private Long pid;
    private String permissionName;
    private String desc;
}
