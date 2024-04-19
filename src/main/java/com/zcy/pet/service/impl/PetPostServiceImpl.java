package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetPostMapper;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.service.PetPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetPostServiceImpl extends ServiceImpl<PetPostMapper, PetPost> implements PetPostService {
    @Override
    public List<PetPost> getAllPetPostList() {
        return this.baseMapper.selectList(null);
    }
}
