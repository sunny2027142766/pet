package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetPost extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long pid;
    private String title;
    private String description;
    private Integer content;
    private String commentNum;
    private String likeNum;
    private String shareNum;
}
