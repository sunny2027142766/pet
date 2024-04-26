package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *  角色表持久化对象
 */
@Data
public class PetInfoBo {
    private Long pid;
    private String name;
    private Long tid;
    private Long mid;
    private String type;
    private Integer isVirtual;
    private String img;
    private Integer age;
    private Double weight;
    private String happy;
    private String health;
    private String hungry;
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
