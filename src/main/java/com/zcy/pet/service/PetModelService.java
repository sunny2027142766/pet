package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetModelVo;

import java.util.List;

public interface PetModelService extends IService<PetModel> {
    List<PetModel> getAllPetModelList();

    IPage<PetModelVo> getPetModelPageList(PetModelPageQuery petModelPageQuery);
}
