package com.zcy.pet.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class PermissionListVo {
    private List<String> permissions;
}
