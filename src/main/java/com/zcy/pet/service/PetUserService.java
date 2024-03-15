package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetUserVo;

import java.util.List;

public interface PetUserService extends IService<PetUser> {
    /**
     * 获取所有用户信息
     */
    List<PetUserVo> getAllPetUser();
}
