package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetUserMapper extends BaseMapper<PetUser> {
    /**
     * 获取所有数据
     */
    List<PetUser> getAllPetUser();
}
