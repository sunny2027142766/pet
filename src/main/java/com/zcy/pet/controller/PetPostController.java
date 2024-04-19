package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.service.PetPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<List<PetPost>> getAllPosts(){
        //  调用service查询所有结果
        List<PetPost> list = petPostService.getAllPetPostList();
        // 封装结果
        return Result.success(list);
    }
}
