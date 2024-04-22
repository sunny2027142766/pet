package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "权限表单对象")
@Data
public class PermForm {

    @Schema(description = "权限ID")
    private Long pid;

    @Schema(description = "权限名称")
    private String permName;

    @Schema(description = "权限编码")
    private String permCode;

    @Schema(description = "描述")
    private String description;
}
