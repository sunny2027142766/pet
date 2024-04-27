package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "图标表单对象")
@Data
public class IconForm {
    @Schema(description = "名称")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "路径")
    private String path;
}
