package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetPostLike extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Long lid; // 点赞表ID
    private Long uid; // 用户ID
    private Long pid; // 帖子ID
    private int status; // 点赞状态
}
