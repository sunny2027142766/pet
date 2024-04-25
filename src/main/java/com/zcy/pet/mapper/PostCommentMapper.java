package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetPostComment;
import com.zcy.pet.model.entity.PetPostShare;
import com.zcy.pet.model.vo.PetCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCommentMapper extends BaseMapper<PetPostComment> {
    List<PetCommentVo> getPostComments(@Param("pid") Long pid);
}
