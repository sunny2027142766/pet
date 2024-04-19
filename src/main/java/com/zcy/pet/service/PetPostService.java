package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetPost;

import java.util.List;

public interface PetPostService extends IService<PetPost> {
    List<PetPost> getAllPetPostList();
}
