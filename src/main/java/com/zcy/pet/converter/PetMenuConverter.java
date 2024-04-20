package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.vo.PetMenuVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMenuConverter {
    List<PetMenuVo> entityToVo(List<PetMenu> menus);

    Page<PetMenuVo> boToPageVo(Page<PetMenuBo> bo);
}
