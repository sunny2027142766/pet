package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetPostVo;
import com.zcy.pet.model.vo.PetRolePageVo;

import java.util.List;

public interface PetPostService extends IService<PetPost> {
    List<PetPost> getAllPetPostList();

    /**
     * 分页查询
     * @param petPostPageQuery 查询条件
     */
    IPage<PetPostVo> getPetPostPageList(PetPostPageQuery petPostPageQuery);
}
