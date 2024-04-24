package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *  类型持久化对象
 */
@Data
public class PetTypeBo {
    private Long tid;
    private String name;
    private String subName;
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
