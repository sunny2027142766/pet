package com.zcy.pet.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.stereotype.Component;

@Component
public class CodeUtil {
    /**
     * 生成指定长度的验证码
     * @param length 长度
     * @return
     */
    public static String generateCode(int length){
        return UUID.randomUUID().toString().substring(0, length);
    }
}