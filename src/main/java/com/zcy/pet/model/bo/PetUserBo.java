package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  用户表持久化对象
 */
@Data
public class PetUserBo {
    private Long uid;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String avatar;
    private Integer isValid;
    private String createTime;
    private String updateTime;
}
