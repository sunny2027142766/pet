package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetTest;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetTestVo;

import java.util.List;


public interface PetTestService extends IService<PetTest> {
    /**
     * 获取所有数据
     */
    List<PetTestVo> getAllPetTestList();

    /**
     * 分页查询
     */
    IPage<PetTestPageVo> getPetTestPageList(PetTestPageQuery petTestPageQuery);
}
