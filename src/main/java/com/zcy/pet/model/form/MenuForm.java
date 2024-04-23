package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "菜单表单对象")
@Data
public class MenuForm {

    @Schema(description = "菜单ID")
    private Long mid;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "菜单路径")
    private String path;
}
