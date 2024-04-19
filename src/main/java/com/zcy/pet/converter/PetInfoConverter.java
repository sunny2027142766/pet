package com.zcy.pet.converter;

import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetPermissionVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetInfoConverter {
    List<PetInfoVo> entityToVo(List<PetInfo> petInfos);
}
