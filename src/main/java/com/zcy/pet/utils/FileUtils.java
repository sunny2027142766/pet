package com.zcy.pet.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@Component
public class FileUtils {
    public static String getFileNameWithoutExtension(MultipartFile file) {
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 如果原始文件名为空，则直接返回空字符串
        if (Objects.isNull(originalFilename)) {
            return "";
        }
        // 获取最后一个"."的位置
        int lastIndex = originalFilename.lastIndexOf(".");
        // 如果没有"."，则直接返回原始文件名
        if (lastIndex == -1) {
            return originalFilename;
        }
        // 截取不带后缀的文件名部分
        return originalFilename.substring(0, lastIndex);
    }
}
