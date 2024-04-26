package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.form.PetModelForm;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetModeConfigVo;
import com.zcy.pet.model.vo.PetModelPageVo;
import com.zcy.pet.model.vo.PetModelVo;

import java.util.List;

public interface PetModelService extends IService<PetModel> {
    List<PetModel> getAllPetModelList();

    IPage<PetModelPageVo> getPetModelPageList(PetModelPageQuery petModelPageQuery);

    boolean savePetModel(PetModelForm petModelForm);

    boolean updatePetModel(Long mid, PetModelForm petModelForm);

    List<Option> listModelOptions();

    List<PetModeConfigVo> listModelConfig();
}
