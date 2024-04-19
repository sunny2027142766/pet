package com.zcy.pet.converter;

import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetMenuVo;
import com.zcy.pet.model.vo.PetPermissionVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMenuConverter {
    List<PetMenuVo> entityToVo(List<PetMenu> menus);
}
