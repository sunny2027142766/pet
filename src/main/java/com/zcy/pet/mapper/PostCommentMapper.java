package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetCommentBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetPostComment;
import com.zcy.pet.model.entity.PetPostShare;
import com.zcy.pet.model.query.PetCommentPageQuery;
import com.zcy.pet.model.vo.PetCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCommentMapper extends BaseMapper<PetPostComment> {
    List<PetCommentVo> getPostComments(@Param("pid") Long pid);

    Page<PetCommentBo> getPagePetCommentList(Page<PetCommentBo> page, PetCommentPageQuery queryParams);
}
