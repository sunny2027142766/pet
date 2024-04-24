package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "评论表单对象")
@Data
public class CommentForm {

    @Schema(description = "帖子ID")
    private Long pid;

    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "评论内容")
    private String content;

}
