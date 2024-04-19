package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetMenu;

import java.util.List;

public interface PetMenuService extends IService<PetMenu> {
    List<PetMenu> getAllPetMenuList();
}
