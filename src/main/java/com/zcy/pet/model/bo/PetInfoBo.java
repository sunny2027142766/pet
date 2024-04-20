package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  角色表持久化对象
 */
@Data
public class PetInfoBo {
    private Long pid;
    private String name;
    private String type;
    private Integer virtual;
    private String age;
    private String weight;
    private String happy;
    private String health;
    private String hungry;
    private String isValid;
    private String createTime;
    private String updateTime;
}
