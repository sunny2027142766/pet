package com.zcy.pet.converter;

import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetPermissionVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPermissionConverter {
    List<PetPermissionVo> entityToVo(List<PetPermission> permissions);
}
