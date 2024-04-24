package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.bo.PetTypeBo;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.entity.PetType;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetTypePageQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetTypeMapper extends BaseMapper<PetType> {
    Page<PetTypeBo> getPagePetTypeList(Page<PetTypeBo> page, PetTypePageQuery queryParams);
}
