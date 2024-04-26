package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.bo.PetModelBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.form.PetModelForm;
import com.zcy.pet.model.vo.PetModelPageVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PetModelConverter{


    Page<PetModelPageVo> boToPageVo(Page<PetModelBo> bo);

    PetModel form2Entity(PetModelForm petModelForm);

    List<Option> entities2Options(List<PetModel> modelList);

    @Mappings({
            @Mapping(target = "value", source = "mid"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> entity2Option(PetModel model);

}
