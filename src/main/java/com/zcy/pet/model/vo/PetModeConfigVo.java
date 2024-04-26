package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PetModeConfigVo {
    private Long pid; // 宠物id
    private String name; // 宠物名称
    private String url; // 模型url
    private String img; // 宠物图片
}
