package com.zcy.pet.filter;

import com.zcy.pet.model.vo.UserTokenInfo;
import com.zcy.pet.utils.JwtTokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtFilter implements HandlerInterceptor {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取token

        String token = request.getHeader(jwtTokenUtil.header);

        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtTokenUtil.header);
        }

        if (StringUtils.isEmpty(token)) {
            // 只是简单DEMO，这里直接返回false，可以自己进行添加
            log.error("token 不能为空！");
            return false;
        }

        // 判断token是否超时
        if (jwtTokenUtil.isTokenExpired(token)) {
            log.error("token 已失效！");
            return false;
        }

        // 判断 token 是否已在黑名单
        if (jwtTokenUtil.checkBlacklist(token)) {
            log.error("token 已被加入黑名单！");
            return false;
        }

        // 获取用户信息
        UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(token);
        // 通过用户信息去判断用户状态，等业务
        //TODO 涉及到业务，这里不在阐述

        return true;
    }

}
