package com.zcy.pet.converter;

import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetPostVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPostConverter {
    List<PetPostVo> entityToVo(List<PetPost> posts);
}
