package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.query.PetRolePageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetRoleMapper extends BaseMapper<PetRole> {
    /**
     * 获取所有数据
     */
    List<PetRole> getAllPetRole();

    Page<PetRoleBo> getPagePetRoleList(Page<PetRoleBo> page, PetRolePageQuery queryParams);
}
