package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetMenuVo;
import com.zcy.pet.model.vo.PetModelVo;
import com.zcy.pet.service.PetMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单", description = "菜单相关接口")
@Slf4j
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class PetMenuController {
    private final PetMenuService petMenuService;

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetMenu>> getAllMenus(){
        //  调用service查询所有结果
        List<PetMenu> list = petMenuService.getAllPetMenuList();
        // 封装结果
        return Result.success(list);
    }
    @Operation(description = "分页查询所有菜单接口")
    @GetMapping("/page")
    public PageResult<PetMenuVo> getAllModelPage(@ParameterObject PetMenuPageQuery petMenuPageQuery) {
        //  调用service查询所有结果
        IPage<PetMenuVo> petMenuPageList = petMenuService.getPetMenuPageList(petMenuPageQuery);
        return PageResult.success(petMenuPageList);
    }
}
