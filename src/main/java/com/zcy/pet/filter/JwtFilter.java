package com.zcy.pet.filter;

import com.zcy.pet.common.exception.AuthException;
import com.zcy.pet.common.result.ResultCode;
import com.zcy.pet.common.utils.ResponseUtils;
import com.zcy.pet.model.vo.UserTokenInfo;
import com.zcy.pet.utils.JwtTokenUtil;
import com.zcy.pet.utils.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter implements HandlerInterceptor {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 获取token
        String token = request.getHeader(jwtTokenUtil.header);
        // 截取token
        token = token.replace("Bearer", "");
        log.info("token：{}", token);
        if (!StringUtils.hasLength(token)) {
            log.error("token 不能为空！");
            ResponseUtils.writeErrMsg(response, ResultCode.ACCESS_UNAUTHORIZED);
            return false;
        }
        log.info("token: {}", token);
        // 判断token是否超时
        try {
            if (jwtTokenUtil.isTokenExpired(token)) {
                log.error("token 已失效！");
                ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
                return false;
            }
        } catch (ExpiredJwtException exception) {
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
            return false;
        }

        // 判断 token 是否已在黑名单
        if (jwtTokenUtil.checkBlacklist(token)) {
            log.error("token 已被加入黑名单！");
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_ACCESS_FORBIDDEN);
            return false;
        }
        try {
            // 获取用户信息
            UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(token);
            log.info("用户信息：{}", userInfoToken);
            // 设置userId到request里，后续根据userId，获取用户信息
            request.setAttribute("uid", userInfoToken.getUid());
        } catch (AuthException authException) {
            log.error("token 无效！");
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
            return false;
        }
        return true;
    }
}
