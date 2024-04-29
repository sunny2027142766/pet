package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetHealthInfoVo {
    private Long pid;
    private Integer happy;
    private Integer health;
    private Integer hungry;
}
