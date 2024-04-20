package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.model.vo.PetUserPageVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetRoleConverter {
    List<PetRoleVo> entityToVo(List<PetRole> petRoles);

    Page<PetRolePageVo> boToPageVo(Page<PetRoleBo> bo);
}
