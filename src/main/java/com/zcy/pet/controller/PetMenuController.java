package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.service.PetMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单", description = "菜单相关接口")
@RestController
@RequestMapping("/api/petInfo")
@RequiredArgsConstructor
public class PetMenuController {
    private final PetMenuService petMenuService;

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetMenu>> getAll(){
        //  调用service查询所有结果
        List<PetMenu> list = petMenuService.getAllPetMenuList();
        // 封装结果
        return Result.success(list);
    }
}
