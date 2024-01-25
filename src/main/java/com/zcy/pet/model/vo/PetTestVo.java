package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetTestVo {
    /**
     *  主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;
}
