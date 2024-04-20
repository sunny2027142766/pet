package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;

import java.util.List;

public interface PetRoleService extends IService<PetRole> {
    /**
     * 获取所有用户信息
     */
    List<PetRoleVo> getAllPetRole();

    /**
     * 分页查询
     */
    IPage<PetRolePageVo> getPetRolePageList(PetRolePageQuery petRolePageQuery);
}
