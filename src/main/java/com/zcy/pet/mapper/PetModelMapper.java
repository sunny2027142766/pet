package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetModelBo;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetModeConfigVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetModelMapper extends BaseMapper<PetModel> {

    Page<PetModelBo> getPagePetModelList(Page<PetModelBo> page, PetModelPageQuery queryParams);

    List<PetModeConfigVo> listModelConfig();
}
