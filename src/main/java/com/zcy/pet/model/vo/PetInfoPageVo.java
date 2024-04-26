package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 角色分页vo视图
 */
@Data
public class PetInfoPageVo {
    private Long pid;
    private String name;
    private Long tid;
    private String type;
    private Long mid;
    private Integer isVirtual;
    private String img;
    private Integer age;
    private Double weight;
    private String happy;
    private String health;
    private String hungry;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
