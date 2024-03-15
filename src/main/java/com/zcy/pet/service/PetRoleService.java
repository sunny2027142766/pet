package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.model.vo.PetUserVo;

import java.util.List;

public interface PetRoleService extends IService<PetRole> {
    /**
     * 获取所有用户信息
     */
    List<PetRoleVo> getAllPetRole();
}
