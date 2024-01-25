package com.zcy.pet.model.vo;

import lombok.Data;

/**
 * 测试数据分页vo视图
 */
@Data
public class PetTestPageVo {
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

    /**
     * 更新时间
     */
    private String updateTime;
}
