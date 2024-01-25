package com.zcy.pet.converter;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetTestBo;
import com.zcy.pet.model.entity.PetTest;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetTestVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetTestConverter {
       List<PetTestVo> entityToVo(List<PetTest> petTestList);
       Page<PetTestPageVo> boToPageVo(Page<PetTestBo> bo);
}
