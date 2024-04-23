package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *  权限持久化对象
 */
@Data
public class PetPermissionBo {
    private Long pid;
    private String permName;
    private String permCode;
    private String description;
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
