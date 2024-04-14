package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.common.result.ResultCode;
import com.zcy.pet.model.dto.LoginResult;
import com.zcy.pet.model.form.UserLoginForm;
import com.zcy.pet.model.form.UserRegisterForm;
import com.zcy.pet.model.vo.UserToken;
import com.zcy.pet.model.vo.UserTokenInfo;
import com.zcy.pet.service.AuthService;
import com.zcy.pet.utils.JwtTokenUtil;
import com.zcy.pet.utils.RedisUtil;
import com.zcy.pet.utils.SendCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录模块", description = "登录模块接口")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final RedisUtil redisUtil;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthService authService;

    private final SendCode sendCode;

    @GetMapping("/sendCode/{email}")
    public Result<String> sendCode(@PathVariable("email") String email) throws MessagingException {
        log.info("邮箱码：{}", email);
        //从redis中取出验证码信息
        String code = String.valueOf(redisUtil.get(email));
        log.info("验证码：{}", code);
        if (!code.equals("null")) {
            return Result.success(code, "验证码已发送,请从邮箱查收");
        }
        String sendedCode = sendCode.sendCode(email);
        if (sendedCode != null) {
            return Result.success(sendedCode, "发送成功");
        }
        return Result.failed("邮箱不正确或为空");
    }

    @PostMapping("/register")
    public Result<Object> register(@RequestBody UserRegisterForm userRegisterForm) {
        log.info("注册信息：{}", userRegisterForm);
        String registerMsg = authService.register(userRegisterForm);
        return Result.success(null, registerMsg);
    }

    @PostMapping("/login")
    public Result<LoginResult> login(@RequestBody UserLoginForm userLoginForm) {
        log.info("登录信息：{}", userLoginForm);
        return authService.login(userLoginForm.getEmail(), userLoginForm.getPassword());
    }

    /**
     * 刷新令牌
     */
    @PostMapping("/refreshToken/{refreshToken}")
    public Result<UserToken> refreshToken(@PathVariable("refreshToken") String refreshToken) {
        // 判断token是否超时
        if (jwtTokenUtil.isTokenExpired(refreshToken)) {
            return Result.failed(ResultCode.TOKEN_INVALID);
        }
        // 刷新令牌 放入黑名单
        jwtTokenUtil.addBlacklist(refreshToken, jwtTokenUtil.getExpirationDate(refreshToken));
        // 访问令牌 放入黑名单
        String odlAccessToken = jwtTokenUtil.getAccessTokenByRefresh(refreshToken);
        if (!StringUtils.hasLength(odlAccessToken)) {
            jwtTokenUtil.addBlacklist(odlAccessToken, jwtTokenUtil.getExpirationDate(odlAccessToken));
        }
        // 生成新的 访问令牌 和 刷新令牌
        UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(refreshToken);
        // 生成Token
        UserToken userToken = jwtTokenUtil.createToekns(userInfoToken);
        return Result.success(userToken);
    }


    /**
     * 登出
     */
    @PostMapping("/logOut/{token}")
    public Result<String> logOut(@PathVariable("token") String token) {
        // 放入黑名单
        jwtTokenUtil.addBlacklist(token, jwtTokenUtil.getExpirationDate(token));
        return Result.success("登出成功");
    }

    /**
     * 注销
     */
    @PostMapping("/logOff/{token}")
    public Result<String> logOff(@PathVariable("token") String token) {
        // 修改用户状态
        //TODO 涉及到业务，这里不在阐述

        // 放入黑名单
        jwtTokenUtil.addBlacklist(token, jwtTokenUtil.getExpirationDate(token));

        return Result.success("注销成功");
    }
}
