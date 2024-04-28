package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "菜单表单对象")
@Data
public class MenuForm {

    @Schema(description = "菜单ID")
    private Long mid;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单图标")
    private Long icon;

    @Schema(description = "是否前台菜单")
    private Integer isFront;


    @Schema(description = "菜单排序")
    private Integer sort;

    @Schema(description = "菜单层级")
    private Integer level;


    @Schema(description = "父菜单ID")
    private Long pid;

    @Schema(description = "菜单路径")
    private String path;
}
