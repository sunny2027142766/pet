package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetRoleConverter;
import com.zcy.pet.converter.PetRoleConverter;
import com.zcy.pet.mapper.PetRoleMapper;
import com.zcy.pet.mapper.PetRoleMapper;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.service.PetRoleService;
import com.zcy.pet.service.PetRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetRoleServiceImpl extends ServiceImpl<PetRoleMapper, PetRole> implements PetRoleService {

    private final PetRoleConverter petRoleConverter;

    @Override
    public List<PetRoleVo> getAllPetRole() {
        List<PetRole> petRoles = this.baseMapper.getAllPetRole();
        return petRoleConverter.entityToVo(petRoles);
    }

    @Override
    public IPage<PetRolePageVo> getPetRolePageList(PetRolePageQuery petRolePageQuery) {
        // 构造参数
        Integer pageNum = petRolePageQuery.getPageNum();
        Integer pageSize = petRolePageQuery.getPageSize();
        Page<PetRoleBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetRoleBo> petRoleBo = this.baseMapper.getPagePetRoleList(pageQuery, petRolePageQuery);
        // 数据转换
        return petRoleConverter.boToPageVo(petRoleBo);
    }
}
