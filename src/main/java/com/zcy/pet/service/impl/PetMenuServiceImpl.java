package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetMenuMapper;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.service.PetMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetMenuServiceImpl extends ServiceImpl<PetMenuMapper, PetMenu> implements PetMenuService {
    @Override
    public List<PetMenu> getAllPetMenuList() {
        return this.baseMapper.selectList(null);
    }
}
