package com.zcy.pet.model.entity;

import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetRole  extends BaseEntity {
    private Long rid;
    private String roleRemark;
    private String desc;
    private Integer isValid;
}
