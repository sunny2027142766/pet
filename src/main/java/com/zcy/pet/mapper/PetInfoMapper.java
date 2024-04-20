package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetInfoMapper extends BaseMapper<PetInfo> {
    Page<PetInfoBo> getPagePetInfoList(Page<PetInfoBo> page, PetInfoPageQuery queryParams);
}
