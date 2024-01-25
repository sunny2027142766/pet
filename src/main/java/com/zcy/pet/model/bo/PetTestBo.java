package com.zcy.pet.model.bo;

import lombok.Data;

/**
 *  测试表持久化对象
 */
@Data
public class PetTestBo {
    private Long id;
    private String name;
    private Integer age;
    private String createTime;
    private String updateTime;
}
