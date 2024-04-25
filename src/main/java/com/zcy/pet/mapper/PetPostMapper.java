package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.query.PetPostQuery;
import com.zcy.pet.model.vo.PetPostDetailVo;
import com.zcy.pet.model.vo.PetPostVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetPostMapper extends BaseMapper<PetPost> {

    Page<PetPostBo> getPagePetPostList(Page<PetPostBo> page, PetPostPageQuery queryParams);

    List<PetPostVo> getAllPostList(@Param("title") String title);

    PetPostDetailVo getPetPostDetail(@Param("pid") Long pid);
}
