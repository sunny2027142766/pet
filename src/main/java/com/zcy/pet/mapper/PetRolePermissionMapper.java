package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetRolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetRolePermissionMapper extends BaseMapper<PetRolePermission> {
    int countRolesForPerm(Long pid);
}
