package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetInfo extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long pid;
    private String name;
    private Long type;
    private Long model;
    private Integer isVirtual;
    private String img;
    private Integer age;
    private Double weight;
    private Integer happy;
    private Integer health;
    private Integer hungry;
    private Integer status;
    /**
     * 逻辑删除标识(0:未删除;1:已删除)
     */
    @TableLogic(delval = "1", value = "0")
    private Integer deleted;
}
