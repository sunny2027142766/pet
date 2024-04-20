package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetModel extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long mid;
    private String name;
    private String url;
    private String img;
    private String isValid;
}
