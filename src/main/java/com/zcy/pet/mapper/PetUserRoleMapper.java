package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetUserRoleMapper extends BaseMapper<PetUserRole> {

    int countUsersForRole(Long roleId);

}
