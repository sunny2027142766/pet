package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    private Integer status;
    private String roleNames;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
