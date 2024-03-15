package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetRoleConverter;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.mapper.PetRoleMapper;
import com.zcy.pet.mapper.PetUserMapper;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.model.vo.PetUserVo;
import com.zcy.pet.service.PetRoleService;
import com.zcy.pet.service.PetUserService;
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
}
