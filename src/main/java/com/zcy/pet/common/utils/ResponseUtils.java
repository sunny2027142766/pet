package com.zcy.pet.common.utils;

import cn.hutool.json.JSONUtil;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.common.result.ResultCode;
import com.zcy.pet.model.dto.LoginResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ResponseUtils {

    /**
     * 异常消息返回(适用过滤器中处理异常响应)
     *
     * @param response
     * @param resultCode
     */
    public static void writeErrMsg(HttpServletResponse response, ResultCode resultCode) throws IOException {
        switch (resultCode) {
            case ACCESS_UNAUTHORIZED, TOKEN_INVALID -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
            case TOKEN_ACCESS_FORBIDDEN -> response.setStatus(HttpStatus.FORBIDDEN.value());
            default -> response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONUtil.toJsonStr(Result.failed(resultCode)));
    }

}

