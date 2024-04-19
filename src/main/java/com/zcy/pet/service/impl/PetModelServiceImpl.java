package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetModelMapper;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.service.PetModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetModelServiceImpl extends ServiceImpl<PetModelMapper, PetModel> implements PetModelService {
    @Override
    public List<PetModel> getAllPetModelList() {
        return this.baseMapper.selectList(null);
    }
}
