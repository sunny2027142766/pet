package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetInfoVo {
    private Long pid;
    private String name;
    private Integer type;
    private Integer isVirtual;
    private String img;
    private Integer age;
    private Double weight;
    private String happy;
    private String health;
    private String hungry;
    private Integer status;
}
