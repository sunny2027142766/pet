package com.zcy.pet.converter;

import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.vo.PetRoleVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetRoleConverter {
    List<PetRoleVo> entityToVo(List<PetRole> petRoles);
}
