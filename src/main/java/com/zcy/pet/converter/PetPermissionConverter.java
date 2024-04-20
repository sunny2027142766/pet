package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPermissionBo;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetRolePageVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPermissionConverter {
    List<PetPermissionVo> entityToVo(List<PetPermission> permissions);

    Page<PetPermissionVo> boToPageVo(Page<PetPermissionBo> bo);
}
