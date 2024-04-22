package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetTestBo;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.PetUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetUserMapper extends BaseMapper<PetUser> {
    /**
     * 获取所有数据
     */
    List<PetUser> getAllPetUser();

    /**
     * 分页查询
     */
    Page<PetUserBo> getPagePetUserList(Page<PetUserBo> page, PetUserPageQuery queryParams);
}
