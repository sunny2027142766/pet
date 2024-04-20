package com.zcy.pet.model.vo;

import lombok.Data;

/**
 * 用户信息分页vo视图
 */
@Data
public class PetUserPageVo {
    private Long uid;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String avatar;
    private Integer isValid;
}
