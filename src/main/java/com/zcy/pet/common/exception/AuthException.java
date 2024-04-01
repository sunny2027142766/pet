package com.zcy.pet.common.exception;

import com.zcy.pet.common.result.IResultCode;
import lombok.Getter;

@Getter
public class AuthException  extends RuntimeException{

    public IResultCode resultCode;
    public AuthException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }
}
