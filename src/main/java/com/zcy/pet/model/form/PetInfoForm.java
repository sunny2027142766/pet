package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "宠物信息表单对象")
@Data
public class PetInfoForm {

    @Schema(description = "宠物ID")
    private Long pid;

    @Schema(description = "宠物名称")
    private String name;

    @Schema(description = "宠物类型")
    private Integer type;

    @Schema(description = "模型类型")
    private Integer isVirtual;

    @Schema(description = "宠物图像")
    private String img;

    @Schema(description = "宠物年龄")
    private Double age;

    @Schema(description = "宠物体重")
    private Double weight;

}
