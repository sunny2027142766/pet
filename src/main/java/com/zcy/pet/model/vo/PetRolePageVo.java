package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 角色分页vo视图
 */
@Data
public class PetRolePageVo {
    private Long rid;
    private String roleName;
    private String roleCode;
    private String description;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
