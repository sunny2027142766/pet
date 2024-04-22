package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetRoleConverter {
    List<PetRoleVo> entityToVo(List<PetRole> petRoles);

    Page<PetRolePageVo> boToPageVo(Page<PetRoleBo> bo);

    @Mappings({
            @Mapping(target = "value", source = "rid"),
            @Mapping(target = "label", source = "roleName")
    })
    Option<Long> entity2Option(PetRole role);

    List<Option> entities2Options(List<PetRole> roles);

    PetRole form2Entity(RoleForm roleForm);
}
