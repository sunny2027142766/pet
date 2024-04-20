package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetUserPageVo;
import com.zcy.pet.model.vo.PetUserVo;

import java.util.List;

public interface PetUserService extends IService<PetUser> {
    /**
     * 获取所有用户信息
     */
    List<PetUserVo> getAllPetUser();

    PetUser getUserByEmail(String email);

    PetUserVo getUserById(Integer id);

    boolean updateUserById(PetUser petUser);

    IPage<PetUserPageVo> getPetUserPageList(PetUserPageQuery petUserPageQuery);
}
