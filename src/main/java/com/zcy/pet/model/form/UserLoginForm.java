package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(description = "登录表单")
@Data
public class UserLoginForm {
    @Schema(description = "用户名")
    private String email;

    @Schema(description = "密码")
    private String password;
}