package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetInfoConverter;
import com.zcy.pet.converter.PetPostConverter;
import com.zcy.pet.mapper.PetInfoMapper;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.service.PetInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetInfoServiceImpl extends ServiceImpl<PetInfoMapper, PetInfo> implements PetInfoService {
    private final PetInfoConverter petInfoConverter;
    @Override
    public List<PetInfo> getAllPetInfoList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetInfoVo> getPetInfoPageList(PetInfoPageQuery petInfoPageQuery) {
        // 构造参数
        Integer pageNum = petInfoPageQuery.getPageNum();
        Integer pageSize = petInfoPageQuery.getPageSize();
        Page<PetInfoBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetInfoBo> petInfoBo = this.baseMapper.getPagePetInfoList(pageQuery, petInfoPageQuery);
        // 数据转换
        return petInfoConverter.boToPageVo(petInfoBo);
    }
}
