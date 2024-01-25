package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetTestBo;
import com.zcy.pet.model.entity.PetTest;
import com.zcy.pet.model.query.PetTestPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetTestMapper extends BaseMapper<PetTest> {

    /**
     * 获取所有数据
     */
    List<PetTest> getAllPetTestList();

    Page<PetTestBo> getPagePetTestList(Page<PetTestBo> page, PetTestPageQuery queryParams);
}
