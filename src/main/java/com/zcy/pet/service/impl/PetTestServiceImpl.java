package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetTestConverter;
import com.zcy.pet.mapper.PetTestMapper;
import com.zcy.pet.model.bo.PetTestBo;
import com.zcy.pet.model.entity.PetTest;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetTestVo;
import com.zcy.pet.service.PetTestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetTestServiceImpl extends ServiceImpl<PetTestMapper, PetTest> implements PetTestService {
    private final PetTestConverter petTestConverter;

    @Override
    public List<PetTestVo> getAllPetTestList() {
        List<PetTest> petTestList = this.baseMapper.getAllPetTestList();
        return petTestConverter.entityToVo(petTestList);
    }

    @Override
    public IPage<PetTestPageVo> getPetTestPageList(PetTestPageQuery petTestPageQuery) {
        // 构造参数
        Integer pageNum = petTestPageQuery.getPageNum();
        Integer pageSize = petTestPageQuery.getPageSize();
        Page<PetTestBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetTestBo> petTestBo = this.baseMapper.getPagePetTestList(pageQuery, petTestPageQuery);
        // 数据转换
        return petTestConverter.boToPageVo(petTestBo);
    }
}
