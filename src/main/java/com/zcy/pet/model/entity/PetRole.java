package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetRole  extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long rid;
    private String roleName;
    private String roleRemark;
    private String desc;
    private Integer isValid;
}
