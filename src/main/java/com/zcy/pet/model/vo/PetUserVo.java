package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetUserVo {
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
}
