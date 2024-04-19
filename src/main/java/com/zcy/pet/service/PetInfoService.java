package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetInfo;

import java.util.List;

public interface PetInfoService extends IService<PetInfo> {
    List<PetInfo> getAllPetInfoList();
}
