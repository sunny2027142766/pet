package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.form.PetInfoForm;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.vo.PetInfoPageVo;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetPostVo;

import java.util.List;

public interface PetInfoService extends IService<PetInfo> {
    List<PetInfo> getAllPetInfoList();

    IPage<PetInfoPageVo> getPetInfoPageList(PetInfoPageQuery petInfoPageQuery);

    boolean savePetInfo(PetInfoForm petInfoForm);

    boolean updatePetInfo(Long pid, PetInfoForm petInfoForm);

    boolean deletePetInfo(String ids);
}
