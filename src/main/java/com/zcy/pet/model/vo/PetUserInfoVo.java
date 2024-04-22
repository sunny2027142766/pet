package com.zcy.pet.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="当前登录用户视图对象")
@Data
public class PetUserInfoVo {
    @Schema(description="用户ID")
    private Long uid;

    @Schema(description="用户名")
    private String username;

    @Schema(description="用户昵称")
    private String nickName;

    @Schema(description="邮箱")
    private String email;

    @Schema(description="电话")
    private String phone;

    @Schema(description="头像")
    private String avatar;

    @Schema(description="用户角色编码集合")
    private String roles;

    @Schema(description="用户权限标识集合")
    private String perms;
}
