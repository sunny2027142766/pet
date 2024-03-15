package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetRoleMapper extends BaseMapper<PetRole> {
    /**
     * 获取所有数据
     */
    List<PetRole> getAllPetRole();
}
