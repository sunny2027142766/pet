package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetUserPageVo;
import com.zcy.pet.model.vo.PetUserVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetUserConverter {
    List<PetUserVo> entityToVo(List<PetUser> petUsers);
    PetUserVo entityToVo(PetUser petUsers);
    Page<PetUserPageVo> boToPageVo(Page<PetUserBo> bo);
}
