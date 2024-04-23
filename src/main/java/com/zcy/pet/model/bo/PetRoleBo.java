package com.zcy.pet.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *  角色表持久化对象
 */
@Data
public class PetRoleBo {
    private Long rid;
    private String roleName;
    private String roleCode;
    private String description;
    private Integer status;
    private String pids;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
