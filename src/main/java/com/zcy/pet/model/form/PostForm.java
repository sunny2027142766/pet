package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "帖子表单对象")
@Data
public class PostForm {

    @Schema(description = "帖子ID")
    private Long pid;

    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "帖子标题")
    private String title;

    @Schema(description = "帖子描述")
    private String description;

    @Schema(description = "帖子内容")
    private String content;

}
