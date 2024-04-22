package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  角色表持久化对象
 */
@Data
public class PetPostBo {
    private Long pid;
    private String title;
    private String description;
    private Integer content;
    private String commentNum;
    private String likeNum;
    private String shareNum;
    private Integer isValid;
    private String createTime;
    private String updateTime;
}
