package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.entity.PetType;
import com.zcy.pet.model.form.PetInfoForm;
import com.zcy.pet.model.form.TypeForm;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.query.PetTypePageQuery;
import com.zcy.pet.model.vo.PetInfoPageVo;
import com.zcy.pet.model.vo.PetTypePageVo;

import java.util.List;

public interface PetTypeService extends IService<PetType> {
    IPage<PetTypePageVo> getPetTypePageList(PetTypePageQuery petTypePageQuery);

    boolean saveType(TypeForm typeForm);

    boolean updateType(Long tid, TypeForm typeForm);

    boolean deleteTypes(String ids);

    List<Option> listTypeOptions();
}
