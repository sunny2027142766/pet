package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetPermissionConverter;
import com.zcy.pet.mapper.PetPermissionMapper;
import com.zcy.pet.model.bo.PetPermissionBo;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.query.PetPermPageQuery;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.service.PetPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetPermissionServiceImpl extends ServiceImpl<PetPermissionMapper, PetPermission> implements PetPermissionService {
    private final PetPermissionConverter petPermissionConverter;


    @Override
    public List<PetPermissionVo> getAllPetPermissionList() {
        List<PetPermission> petPermissionList = this.baseMapper.selectList(null);
        return petPermissionConverter.entityToVo(petPermissionList);
    }

    @Override
    public IPage<PetPermissionVo> getPetPermPageList(PetPermPageQuery petPermPageQuery) {
        // 构造参数
        Integer pageNum = petPermPageQuery.getPageNum();
        Integer pageSize = petPermPageQuery.getPageSize();
        Page<PetPermissionBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetPermissionBo> petPermissionBo = this.baseMapper.getPagePetPermList(pageQuery, petPermPageQuery);
        // 数据转换
        return petPermissionConverter.boToPageVo(petPermissionBo);
    }
}
