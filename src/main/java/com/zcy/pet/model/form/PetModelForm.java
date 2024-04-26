package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "宠物模型表单对象")
@Data
public class PetModelForm {

    @Schema(description = "模型ID")
    private Long mid;

    @Schema(description = "模型名称")
    private String name;

    @Schema(description = "模型地址")
    private String url;



}
