package com.zcy.pet.model.entity;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zcy.pet.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pet_user",autoResultMap = true)
public class PetUser extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long uid;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private String avatar;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject config;

    private String address;

    private Integer sex;

    private String birthday;

    private Integer status;
    /**
     * 逻辑删除标识(0:未删除;1:已删除)
     */
    @TableLogic(delval = "1", value = "0")
    private Integer deleted;
}
