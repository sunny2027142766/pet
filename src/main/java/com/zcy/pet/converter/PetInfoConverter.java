package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.form.PetInfoForm;
import com.zcy.pet.model.vo.PetInfoPageVo;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetPostVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetInfoConverter {
    List<PetInfoVo> entityToVo(List<PetInfo> petInfos);

    Page<PetInfoPageVo> boToPageVo(Page<PetInfoBo> bo);

    PetInfo form2Entity(PetInfoForm petInfoForm);

}
