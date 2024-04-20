package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  角色表持久化对象
 */
@Data
public class PetModelBo {
    private Long mid;
    private String name;
    private String url;
    private String img;
    private String isValid;
    private String createTime;
    private String updateTime;
}
