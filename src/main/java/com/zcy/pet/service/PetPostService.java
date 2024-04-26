package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.form.CommentForm;
import com.zcy.pet.model.form.PostForm;
import com.zcy.pet.model.query.PetCommentPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.query.PetPostQuery;
import com.zcy.pet.model.vo.*;

import java.util.List;

public interface PetPostService extends IService<PetPost> {
    List<PetPostVo> getAllPetPostList(String title);

    /**
     * 分页查询
     * @param petPostPageQuery 查询条件
     */
    IPage<PetPostPageVo> getPetPostPageList(PetPostPageQuery petPostPageQuery);

    boolean savePost(PostForm postForm);

    void likePost(Long pid, Long uid,Integer status);

    void sharePost(Long pid, Long uid);

    boolean commentPost(CommentForm commentForm);

    PetPostDetailVo getAllPetPostDetail(Long pid);

    List<PetCommentVo> getPostComments(Long pid);

    IPage<PetCommentPageVo> getAllPostComments(PetCommentPageQuery petCommentPageQuery);

    boolean deleteComments(String ids);

    boolean deletePosts(String ids);
}
