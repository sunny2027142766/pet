package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetInfoVo {
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
}
