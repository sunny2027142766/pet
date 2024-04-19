package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetPermissionVo;

import java.util.List;

public interface PetPermissionService extends IService<PetPermission> {
    List<PetPermissionVo> getAllPetPermissionList();
}
