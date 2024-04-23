package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetPermMenu;
import com.zcy.pet.model.entity.PetRolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetPermMenuMapper extends BaseMapper<PetPermMenu> {
    int countMenusForPerm(Long mid);
}
