package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.form.CommentForm;
import com.zcy.pet.model.form.PostForm;
import com.zcy.pet.model.form.TypeForm;
import com.zcy.pet.model.query.PetPermPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.query.PetPostQuery;
import com.zcy.pet.model.vo.*;
import com.zcy.pet.service.PetPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "社区帖子", description = "社区帖子相关接口")
@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PetPostController {
    private final PetPostService petPostService;

    @Operation(description = "查询所有帖子")
    @GetMapping
    public Result<List<PetPostVo>> getAllPosts(@RequestParam String title) {
        //  调用service查询所有结果
        List<PetPostVo> list = petPostService.getAllPetPostList(title);
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "查询帖子详细信息")
    @GetMapping("{pid}")
    public Result<PetPostDetailVo> getAllPosts(@Parameter(description = "帖子ID") @PathVariable Long pid) {
        //  调用service查询所有结果
        PetPostDetailVo list = petPostService.getAllPetPostDetail(pid);
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "查询帖子评论列表")
    @GetMapping("{pid}/comment")
    public Result<List<PetCommentVo>> getPostComments(@Parameter(description = "帖子ID") @PathVariable Long pid) {
        //  调用service查询所有结果
        List<PetCommentVo> list = petPostService.getPostComments(pid);
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "分页查询所有帖子接口")
    @GetMapping("/page")
    public PageResult<PetPostPageVo> getAllPostPage(@ParameterObject PetPostPageQuery petPostPageQuery) {
        //  调用service查询所有结果
        IPage<PetPostPageVo> petPostPageList = petPostService.getPetPostPageList(petPostPageQuery);
        return PageResult.success(petPostPageList);
    }

    @Operation(description = "添加帖子")
    @PostMapping
    public Result addPost(@Valid @RequestBody PostForm postForm) {
        boolean result = petPostService.savePost(postForm);
        return Result.judge(result);
    }

    @Operation(description = "点赞帖子")
    @GetMapping("/like")
    public Result likePost(@RequestParam("pid") Long pid, @RequestParam("uid") Long uid,@RequestParam("status") Integer status) {
        petPostService.likePost(pid, uid,status);
        return Result.success(null);
    }

    @Operation(description = "分享帖子")
    @GetMapping("/share")
    public Result sharePost( @RequestParam("pid") Long pid,@RequestParam("uid") Long uid){
        petPostService.sharePost(pid, uid);
        return Result.success(null);
    }

    @Operation(description = "评论帖子")
    @PostMapping("/comment")
    public Result commentPost(@Valid @RequestBody CommentForm commentForm){
        boolean result =  petPostService.commentPost(commentForm);
        return Result.judge(result);
    }

    // TODO: 删除帖子
}

