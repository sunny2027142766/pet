package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetModelVo;
import com.zcy.pet.service.PetModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "宠物模型", description = "宠物模型相关接口")
@Slf4j
@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class PetModelController {
    private final PetModelService petModelService;

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetModel>> getAllModels(){
        //  调用service查询所有结果
        List<PetModel> list = petModelService.getAllPetModelList();
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "分页查询所有宠物模型接口")
    @GetMapping("/page")
    public PageResult<PetModelVo> getAllModelPage(@ParameterObject PetModelPageQuery petModelPageQuery) {
        //  调用service查询所有结果
        IPage<PetModelVo> petModelPageList = petModelService.getPetModelPageList(petModelPageQuery);
        return PageResult.success(petModelPageList);
    }
}
