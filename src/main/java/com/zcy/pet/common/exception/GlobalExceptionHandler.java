package com.zcy.pet.common.exception;

import com.zcy.pet.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> handleAuthException(AuthException e) {
        log.error("异常原因：{}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public <T> Result<T> handleIllegalArgumentException(Exception e) {
        log.error("异常原因：{}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }
}
