package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.form.UserForm;
import com.zcy.pet.model.vo.PetUserInfoVo;
import com.zcy.pet.model.vo.PetUserPageVo;
import com.zcy.pet.model.vo.PetUserVo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetUserConverter {
    List<PetUserVo> entityToVo(List<PetUser> petUsers);
    PetUserVo entityToVo(PetUser petUsers);
    Page<PetUserPageVo> boToPageVo(Page<PetUserBo> bo);
    PetUserInfoVo toUserInfoVo(PetUser entity);
    @InheritInverseConfiguration(name = "entity2Form")
    PetUser form2Entity(UserForm entity);
}
