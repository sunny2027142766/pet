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
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetPostPageVo;
import com.zcy.pet.model.vo.PetPostVo;
import com.zcy.pet.service.PetPostService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetPost>> getAllPosts() {
        //  调用service查询所有结果
        List<PetPost> list = petPostService.getAllPetPostList();
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
    public Result likePost(@RequestParam("pid") Long pid, @RequestParam("uid") Long uid,@RequestParam("pid") Integer status) {
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
}

