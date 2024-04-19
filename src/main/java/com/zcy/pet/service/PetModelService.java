package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetModel;

import java.util.List;

public interface PetModelService extends IService<PetModel> {
    List<PetModel> getAllPetModelList();
}
