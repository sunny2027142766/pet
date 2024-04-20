package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPermissionBo;
import com.zcy.pet.model.bo.PetTestBo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.query.PetPermPageQuery;
import com.zcy.pet.model.query.PetTestPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetPermissionMapper extends BaseMapper<PetPermission> {
    List<PetPermission> getAllPetPermission();

    Page<PetPermissionBo> getPagePetPermList(Page<PetPermissionBo> page, PetPermPageQuery queryParams);
}
