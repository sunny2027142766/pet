package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "注册表单")
@Data
public class UserRegisterForm {
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "验证码")
    private String code;

}