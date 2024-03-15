package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.vo.PetUserVo;
import com.zcy.pet.service.PetUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "用户模块", description = "用户模块接口")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class PetUserController {
    private final PetUserService petUserService;

    @Operation(description = "查询所有用户接口")
    @GetMapping
    public Result<List<PetUserVo>> getAllUsers(){
        //  调用service查询所有结果
        List<PetUserVo> list = petUserService.getAllPetUser();
        // 封装结果
        return Result.success(list);
    }
}
