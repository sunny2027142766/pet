package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetInfoConverter;
import com.zcy.pet.converter.PetMenuConverter;
import com.zcy.pet.mapper.PetMenuMapper;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.vo.PetMenuVo;
import com.zcy.pet.service.PetMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetMenuServiceImpl extends ServiceImpl<PetMenuMapper, PetMenu> implements PetMenuService {
    private final PetMenuConverter petMenuConverter;
    @Override
    public List<PetMenu> getAllPetMenuList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetMenuVo> getPetMenuPageList(PetMenuPageQuery petMenuPageQuery) {
        // 构造参数
        Integer pageNum = petMenuPageQuery.getPageNum();
        Integer pageSize = petMenuPageQuery.getPageSize();
        Page<PetMenuBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetMenuBo> petMenuBo = this.baseMapper.getPagePetMenuList(pageQuery, petMenuPageQuery);
        // 数据转换
        return petMenuConverter.boToPageVo(petMenuBo);
    }
}
