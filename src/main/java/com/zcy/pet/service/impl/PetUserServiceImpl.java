package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.mapper.PetUserMapper;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetUserVo;
import com.zcy.pet.service.PetUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetUserServiceImpl extends ServiceImpl<PetUserMapper, PetUser> implements PetUserService {

    private final PetUserConverter petUserConverter;

    @Override
    public List<PetUserVo> getAllPetUser() {
        List<PetUser> petUsers = this.baseMapper.getAllPetUser();
        return petUserConverter.entityToVo(petUsers);
    }

    @Override
    public PetUser getUserByEmail(String email) {
        QueryWrapper<PetUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        PetUser petUser = this.getOne(queryWrapper);
        // 根据邮箱获取用户是否存在
        return petUser;
    }
}
