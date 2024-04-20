package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetModelBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.vo.PetModelVo;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetPostVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetModelConverter {
    List<PetModelVo> entityToVo(List<PetModel> models);

    Page<PetModelVo> boToPageVo(Page<PetModelBo> bo);
}
