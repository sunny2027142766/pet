package com.zcy.pet.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetUser extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long uid;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private Integer isValid;
}
