package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
