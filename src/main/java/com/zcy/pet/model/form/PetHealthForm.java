package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "健康值表单对象")
@Data
public class PetHealthForm {
    private Long pid;

    private Integer health;

    private Integer happy;

    private Integer hungry;
}
