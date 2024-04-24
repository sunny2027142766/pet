package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetPostComment extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Long cid;
    private Long uid;
    private Long pid;
    private String content;
    private Integer deleted;
}
