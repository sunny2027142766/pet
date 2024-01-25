package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PetTest extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
}
