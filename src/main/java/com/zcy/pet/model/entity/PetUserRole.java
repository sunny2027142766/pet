package com.zcy.pet.model.entity;

import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetUserRole extends BaseEntity {
    private Long id;
    private Long uid;
    private Long rid;
}
