package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.bo.PetModelBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetMenuMapper extends BaseMapper<PetMenu> {
    Page<PetMenuBo> getPagePetMenuList(Page<PetMenuBo> page, PetMenuPageQuery queryParams);
}
