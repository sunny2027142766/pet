package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetPermission extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long pid;
    private String permName;
    private String permCode;
    private String description;
}
