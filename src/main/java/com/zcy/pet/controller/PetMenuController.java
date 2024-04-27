package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.form.PermForm;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetMenuPageVo;
import com.zcy.pet.model.vo.PetMenuTreeVo;
import com.zcy.pet.model.vo.PetMenuVo;
import com.zcy.pet.model.vo.PetModelVo;
import com.zcy.pet.service.PetMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

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
    public PageResult<PetMenuPageVo> getAllMenuPage(@ParameterObject PetMenuPageQuery petMenuPageQuery) {
        //  调用service查询所有结果
        IPage<PetMenuPageVo> petMenuPageList = petMenuService.getPetMenuPageList(petMenuPageQuery);
        return PageResult.success(petMenuPageList);
    }


    @Operation(description = "获取所有菜单树型结构接口")
    @GetMapping("/tree")
    public PageResult<PetMenuTreeVo> getAllMenuTree(@ParameterObject PetMenuPageQuery petMenuPageQuery) {
        //  调用service查询所有结果
        IPage<PetMenuTreeVo> petMenuTreeList = petMenuService.getPetMenuTreeList(petMenuPageQuery);
        return PageResult.success(petMenuTreeList);
    }

    @Operation(summary = "新增菜单")
    @PostMapping
    public Result addMenu(@Valid @RequestBody MenuForm menuForm) {
        boolean result = petMenuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单")
    @PutMapping(value = "/{mid}")
    public Result updateMenu(@Parameter(description = "用户ID") @PathVariable Long mid, @Valid @RequestBody MenuForm menuForm) {
        boolean result = petMenuService.updateMenu(mid, menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{ids}")
    public Result deleteMenus(@Parameter(description = "删除菜单，多个以英文逗号(,)拼接") @PathVariable String ids) {
        boolean result = petMenuService.deleteMenus(ids);
        return Result.judge(result);
    }

    @Operation(summary = "菜单下拉列表")
    @GetMapping("/options")
    public Result getMenuOptions(){
        List<Option> list = petMenuService.listMenuOptions();
        return Result.success(list);
    }
}
