package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetPostPageQuery;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetPostVo;
import com.zcy.pet.service.PetInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PageResult<PetInfoVo> getAllInfoPage(@ParameterObject PetInfoPageQuery petInfoPageQuery) {
        //  调用service查询所有结果
        IPage<PetInfoVo> petInfoPageList = petInfoService.getPetInfoPageList(petInfoPageQuery);
        return PageResult.success(petInfoPageList);
    }
}
