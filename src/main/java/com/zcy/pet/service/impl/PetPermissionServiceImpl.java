package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetPermissionConverter;
import com.zcy.pet.mapper.PetPermissionMapper;
import com.zcy.pet.model.entity.PetPermission;
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
}
