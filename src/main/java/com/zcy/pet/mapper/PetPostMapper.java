package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.query.PetRolePageQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetPostMapper extends BaseMapper<PetPost> {

    Page<PetPostBo> getPagePetPostList(Page<PetPostBo> page, PetPostPageQuery queryParams);
}
