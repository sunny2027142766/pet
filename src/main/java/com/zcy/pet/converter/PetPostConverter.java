package com.zcy.pet.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.entity.PetType;
import com.zcy.pet.model.form.PostForm;
import com.zcy.pet.model.form.TypeForm;
import com.zcy.pet.model.vo.PetPostPageVo;
import com.zcy.pet.model.vo.PetPostVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPostConverter {
    List<PetPostVo> entityToVo(List<PetPost> posts);

    Page<PetPostPageVo> boToPageVo(Page<PetPostBo> bo);

    PetPost form2Entity(PostForm postForm);
}
