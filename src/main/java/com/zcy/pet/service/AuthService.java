package com.zcy.pet.service;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.dto.LoginResult;
import com.zcy.pet.model.form.UserRegisterForm;

public interface AuthService{

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return 用户登录信息
     */
    Result<LoginResult> login(String email, String password);

    String register(UserRegisterForm userRegisterForm);
}
