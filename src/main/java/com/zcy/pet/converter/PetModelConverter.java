package com.zcy.pet.converter;

import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetModelVo;
import com.zcy.pet.model.vo.PetPermissionVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetModelConverter {
    List<PetModelVo> entityToVo(List<PetModel> models);
}
