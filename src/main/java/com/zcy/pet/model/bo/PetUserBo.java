package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    private Integer status;
    private String roleNames;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
