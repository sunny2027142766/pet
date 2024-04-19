package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.service.PetInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "宠物档案", description = "宠物档案相关接口")
@RestController
@RequestMapping("/api/petInfo")
@RequiredArgsConstructor
public class PetInfoController {
    private final PetInfoService petInfoService;

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetInfo>> getAll(){
        //  调用service查询所有结果
        List<PetInfo> list = petInfoService.getAllPetInfoList();
        // 封装结果
        return Result.success(list);
    }
}
