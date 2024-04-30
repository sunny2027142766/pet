package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "用户表单对象")
@Data
public class UserForm {
    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "角色")
    private List<Long> roleIds;
}
