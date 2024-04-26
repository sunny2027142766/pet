package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetCommentConverter;
import com.zcy.pet.converter.PetPostCommentConverter;
import com.zcy.pet.converter.PetPostConverter;
import com.zcy.pet.mapper.PetPostMapper;
import com.zcy.pet.mapper.PostCommentMapper;
import com.zcy.pet.mapper.PostLikeMapper;
import com.zcy.pet.mapper.PostShareMapper;
import com.zcy.pet.model.bo.PetCommentBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.entity.PetPostComment;
import com.zcy.pet.model.entity.PetPostLike;
import com.zcy.pet.model.entity.PetPostShare;
import com.zcy.pet.model.form.CommentForm;
import com.zcy.pet.model.form.PostForm;
import com.zcy.pet.model.query.PetCommentPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.query.PetPostQuery;
import com.zcy.pet.model.vo.*;
import com.zcy.pet.service.PetPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetPostServiceImpl extends ServiceImpl<PetPostMapper, PetPost> implements PetPostService {

    private final PetPostConverter petPostConverter;

    private final PetPostCommentConverter petPostCommentConverter;

    private final PetCommentConverter petCommentConverter;

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Autowired
    private PostShareMapper postShareMapper;

    @Autowired
    private PostCommentMapper postCommentMapper;

    @Override
    public List<PetPostVo> getAllPetPostList(String title) {
        // zf TODO: 这里关联查询的点赞数,分享数,和评论数,不正确,需要进行问题排查
        return this.baseMapper.getAllPostList(title);
    }

    @Override
    public IPage<PetPostPageVo> getPetPostPageList(PetPostPageQuery petPostPageQuery) {
        // 构造参数
        Integer pageNum = petPostPageQuery.getPageNum();
        Integer pageSize = petPostPageQuery.getPageSize();
        Page<PetPostBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petPostPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetPostBo> petPostBo = this.baseMapper.getPagePetPostList(pageQuery, petPostPageQuery);
        // 数据转换
        return petPostConverter.boToPageVo(petPostBo);
    }

    @Override
    public boolean savePost(PostForm postForm) {
        // 实体转换
        PetPost type = petPostConverter.form2Entity(postForm);
        // 保存数据
        return this.save(type);
    }

    @Override
    public void likePost(Long pid, Long uid, Integer status) {
        // 根据帖子ID和用户ID查询当前的点赞记录
        PetPostLike like = postLikeMapper.selectOne(new QueryWrapper<PetPostLike>()
                .eq("pid", pid)
                .eq("uid", uid));

        if (like == null) {
            // 如果不存在点赞记录，则插入一条新的点赞记录，默认状态为已点赞
            like = new PetPostLike();
            like.setPid(pid);
            like.setUid(uid);
            like.setStatus(1);
            postLikeMapper.insert(like);
        } else {
            // 如果已存在点赞记录,设置传入的状态
            like.setStatus(status);
            postLikeMapper.update(like, new UpdateWrapper<PetPostLike>()
                    .eq("lid", like.getLid())); // 根据主键更新
        }
    }

    @Override
    public void sharePost(Long pid, Long uid) {
        // 向分享表中插入一条新的分享记录
        PetPostShare postShare = new PetPostShare();
        postShare.setPid(pid);
        postShare.setUid(uid);
        postShareMapper.insert(postShare);
    }

    @Override
    public boolean commentPost(CommentForm commentForm) {
        // 增加一条评论记录
        PetPostComment comment = petPostCommentConverter.form2Entity(commentForm);
        int count = postCommentMapper.insert(comment);
        return count > 0;
    }

    @Override
    public PetPostDetailVo getAllPetPostDetail(Long pid) {
        // 根据帖子id查询帖子详情
        return this.baseMapper.getPetPostDetail(pid);
    }

    @Override
    public List<PetCommentVo> getPostComments(Long pid) {
        // 根据帖子id查询该帖子的所有评论
        return postCommentMapper.getPostComments(pid);
    }

    @Override
    public IPage<PetCommentPageVo> getAllPostComments(PetCommentPageQuery petCommentPageQuery) {
        // 构造参数
        Integer pageNum = petCommentPageQuery.getPageNum();
        Integer pageSize = petCommentPageQuery.getPageSize();
        Page<PetCommentBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petCommentPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetCommentBo> petCommentBo = postCommentMapper.getPagePetCommentList(pageQuery, petCommentPageQuery);
        // 数据转换
        return petCommentConverter.boToPageVo(petCommentBo);
    }

    @Override
    public boolean deleteComments(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的数据为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        int count = postCommentMapper.deleteBatchIds(ids);
        return count > 0;
    }

    @Override
    public boolean deletePosts(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的数据为空");
        // 逻辑删除
        // TODO: 删除是否需要把关联的评论表，点赞表，分享表中对应的数据清空？
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        int count = this.baseMapper.deleteBatchIds(ids);
        return count > 0;
    }
}
