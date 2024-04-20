package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetInfoConverter;
import com.zcy.pet.converter.PetModelConverter;
import com.zcy.pet.mapper.PetModelMapper;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetModelBo;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetModelVo;
import com.zcy.pet.service.PetModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetModelServiceImpl extends ServiceImpl<PetModelMapper, PetModel> implements PetModelService {
    private final PetModelConverter petModelConverter;
    @Override
    public List<PetModel> getAllPetModelList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetModelVo> getPetModelPageList(PetModelPageQuery petModelPageQuery) {
        // 构造参数
        Integer pageNum = petModelPageQuery.getPageNum();
        Integer pageSize = petModelPageQuery.getPageSize();
        Page<PetModelBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetModelBo> petModelBo = this.baseMapper.getPagePetModelList(pageQuery, petModelPageQuery);
        // 数据转换
        return petModelConverter.boToPageVo(petModelBo);
    }
}
