package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetInfoMapper;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.service.PetInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetInfoServiceImpl extends ServiceImpl<PetInfoMapper, PetInfo> implements PetInfoService {
    @Override
    public List<PetInfo> getAllPetInfoList() {
        return this.baseMapper.selectList(null);
    }
}
