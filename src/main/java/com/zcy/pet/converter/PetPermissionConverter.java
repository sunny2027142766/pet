package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.bo.PetPermissionBo;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.PermForm;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetRolePageVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPermissionConverter {
    List<PetPermissionVo> entityToVo(List<PetPermission> permissions);

    Page<PetPermissionVo> boToPageVo(Page<PetPermissionBo> bo);

    @Mappings({
            @Mapping(target = "value", source = "pid"),
            @Mapping(target = "label", source = "permName")
    })
    Option<Long> entity2Option(PetPermission permission);

    List<Option> entities2Options(List<PetPermission> permissions);

    PetPermission form2Entity(PermForm permForm);
}
