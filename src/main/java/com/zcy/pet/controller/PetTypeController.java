package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.form.TypeForm;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.query.PetTypePageQuery;
import com.zcy.pet.model.vo.PetMenuPageVo;
import com.zcy.pet.model.vo.PetTypePageVo;
import com.zcy.pet.service.PetMenuService;
import com.zcy.pet.service.PetTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "宠物类型", description = "宠物类型相关接口")
@Slf4j
@RestController
@RequestMapping("/api/type")
@RequiredArgsConstructor
public class PetTypeController {
    private final PetTypeService petTypeService;
    @Operation(description = "分页查询所有宠物类型接口")
    @GetMapping("/page")
    public PageResult<PetTypePageVo> getAllTypePage(@ParameterObject PetTypePageQuery petTypePageQuery) {
        //  调用service查询所有结果
        IPage<PetTypePageVo> petTypePageList = petTypeService.getPetTypePageList(petTypePageQuery);
        return PageResult.success(petTypePageList);
    }
    @Operation(summary = "新增宠物类型")
    @PostMapping
    public Result addType(@Valid @RequestBody TypeForm typeForm) {
        boolean result = petTypeService.saveType(typeForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改类型")
    @PutMapping(value = "/{tid}")
    public Result updateType(@Parameter(description = "类型ID") @PathVariable Long tid, @Valid @RequestBody TypeForm typeForm) {
        boolean result = petTypeService.updateType(tid, typeForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除类型")
    @DeleteMapping("/{ids}")
    public Result deleteTypes(@Parameter(description = "删除菜单，多个以英文逗号(,)拼接") @PathVariable String ids) {
        boolean result = petTypeService.deleteTypes(ids);
        return Result.judge(result);
    }

    @Operation(summary = "类型下拉列表")
    @GetMapping("/options")
    public Result getTypeOptions(){
        List<Option> list = petTypeService.listTypeOptions();
        return Result.success(list);
    }
}
