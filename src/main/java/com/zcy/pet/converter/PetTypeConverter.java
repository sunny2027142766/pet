package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetTypeBo;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetType;
import com.zcy.pet.model.form.PetInfoForm;
import com.zcy.pet.model.form.TypeForm;
import com.zcy.pet.model.vo.PetInfoPageVo;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetTypePageVo;
import com.zcy.pet.model.vo.PetTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetTypeConverter {
    List<PetTypeVo> entityToVo(List<PetType> petTypes);

    Page<PetTypePageVo> boToPageVo(Page<PetTypeBo> bo);

    PetType form2Entity(TypeForm typeForm);

    @Mappings({
            @Mapping(target = "value", source = "tid"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> entity2Option(PetType type);

    List<Option> entities2Options(List<PetType> roles);

}
