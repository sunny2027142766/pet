package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.vo.PetMenuPageVo;
import com.zcy.pet.model.vo.PetMenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMenuConverter {
    List<PetMenuVo> entityToVo(List<PetMenu> menus);

    Page<PetMenuPageVo> boToPageVo(Page<PetMenuBo> bo);

    PetMenu form2Entity(MenuForm menuForm);

    @Mappings({
            @Mapping(target = "value", source = "mid"),
            @Mapping(target = "label", source = "title")
    })
    Option<Long> entity2Option(PetMenu menu);

    List<Option> entities2Options(List<PetMenu> menus);
}
