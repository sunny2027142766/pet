package com.zcy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcy.pet.model.entity.PetPostComment;
import com.zcy.pet.model.entity.PetPostShare;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCommentMapper extends BaseMapper<PetPostComment> {
}
