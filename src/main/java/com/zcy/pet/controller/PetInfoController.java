package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.form.PetHealthForm;
import com.zcy.pet.model.form.PetInfoForm;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.vo.PetHealthInfoVo;
import com.zcy.pet.model.vo.PetInfoPageVo;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetPostVo;
import com.zcy.pet.service.PetInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "宠物档案", description = "宠物档案相关接口")
@Slf4j
@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class PetInfoController {
    private final PetInfoService petInfoService;

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetInfo>> getAllPetInfos(){
        //  调用service查询所有结果
        List<PetInfo> list = petInfoService.getAllPetInfoList();
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "分页查询所有宠物信息接口")
    @GetMapping("/page")
    public PageResult<PetInfoPageVo> getAllInfoPage(@ParameterObject PetInfoPageQuery petInfoPageQuery) {
        //  调用service查询所有结果
        IPage<PetInfoPageVo> petInfoPageList = petInfoService.getPetInfoPageList(petInfoPageQuery);
        return PageResult.success(petInfoPageList);
    }

    @Operation(summary = "新增宠物信息")
    @PostMapping
    public Result addPetInfo(@Valid @RequestBody PetInfoForm petInfoForm) {
        boolean result = petInfoService.savePetInfo(petInfoForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改宠物信息")
    @PutMapping("/{pid}")
    public Result updatePetInfo(@Parameter(description = "宠物信息ID") @PathVariable Long pid, @Valid @RequestBody PetInfoForm petInfoForm) {
        boolean result = petInfoService.updatePetInfo(pid,petInfoForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除类型")
    @DeleteMapping("/{ids}")
    public Result deletePetInfo(@Parameter(description = "删除菜单，多个以英文逗号(,)拼接") @PathVariable String ids) {
        boolean result = petInfoService.deletePetInfo(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取宠物健康信息")
    @GetMapping("/health/{pid}")
    public Result<PetHealthInfoVo> getPetHealthInfo(@Parameter(description = "宠物ID") @PathVariable Long pid) {
        PetHealthInfoVo petHealthInfoVo = petInfoService.getPetHealthInfo(pid);
        return Result.success(petHealthInfoVo);
    }
    @Operation(summary = "更新宠物健康信息")
    @PutMapping("/health/{pid}")
    public Result<Boolean> updatePetHealthInfo(@Parameter(description = "宠物ID") @PathVariable Long pid, @Valid @RequestBody PetHealthForm petHealthForm) {
        Boolean success = petInfoService.updatePetHealthInfo(pid,petHealthForm);
        return Result.success(success);
    }


}
