package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetInfo extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long pid;
    private String name;
    private String type;
    private Integer virtual;
    private String age;
    private String weight;
    private String happy;
    private String health;
    private String hungry;
    private String isValid;
}
