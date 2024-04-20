package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetPostConverter;
import com.zcy.pet.mapper.PetPostMapper;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.vo.PetPostVo;
import com.zcy.pet.service.PetPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetPostServiceImpl extends ServiceImpl<PetPostMapper, PetPost> implements PetPostService {

    private final PetPostConverter petPostConverter;
    @Override
    public List<PetPost> getAllPetPostList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetPostVo> getPetPostPageList(PetPostPageQuery petPostPageQuery) {
        // 构造参数
        Integer pageNum = petPostPageQuery.getPageNum();
        Integer pageSize = petPostPageQuery.getPageSize();
        Page<PetPostBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetPostBo> petPostBo = this.baseMapper.getPagePetPostList(pageQuery, petPostPageQuery);
        // 数据转换
        return petPostConverter.boToPageVo(petPostBo);
    }
}
