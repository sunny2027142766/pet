package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "宠物类型表单对象")
@Data
public class TypeForm {

    @Schema(description = "宠物类型ID")
    private Long tid;

    @Schema(description = "宠物类型名称")
    private String name;

    @Schema(description = "宠物类型子名称")
    private String subName;
}
