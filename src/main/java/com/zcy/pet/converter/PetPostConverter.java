package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetPostVo;
import com.zcy.pet.model.vo.PetRolePageVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPostConverter {
    List<PetPostVo> entityToVo(List<PetPost> posts);

    Page<PetPostVo> boToPageVo(Page<PetPostBo> bo);
}
