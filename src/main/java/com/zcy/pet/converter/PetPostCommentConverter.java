package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.entity.PetPostComment;
import com.zcy.pet.model.form.CommentForm;
import com.zcy.pet.model.form.PostForm;
import com.zcy.pet.model.vo.PetPostPageVo;
import com.zcy.pet.model.vo.PetPostVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPostCommentConverter {
    List<PetPostVo> entityToVo(List<PetPost> posts);

    Page<PetPostPageVo> boToPageVo(Page<PetPostBo> bo);

    PetPostComment form2Entity(CommentForm commentForm);
}
