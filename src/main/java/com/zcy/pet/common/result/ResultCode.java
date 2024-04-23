package com.zcy.pet.common.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {
    SUCCESS(200, "请求成功"),
    SYSTEM_EXECUTION_ERROR(500, "系统执行出错"),

    TOKEN_INVALID(401,"未授权，请重新登录"),
    ACCESS_UNAUTHORIZED(10011, "访问未授权"),
    TOKEN_ACCESS_FORBIDDEN(10012, "token已被禁止访问"),

    USERNAME_PASSWORD_IS_NULL(10020,"用户名或密码为空"),
    USER_NOT_EXIST(10030,"用户不存在");




    private int code;

    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }


    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }
}
