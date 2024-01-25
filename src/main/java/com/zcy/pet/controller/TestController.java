package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetTestVo;
import com.zcy.pet.service.PetTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "测试模块", description = "测试模块接口")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PetTestService petTestService;

    @Operation(description = "测试hello接口")
    @GetMapping("/hello")
    public String Hello(){
        System.out.println("Hello");
        return "Hello world";
    }

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetTestVo>> getList(){
        //  调用service查询所有结果
        List<PetTestVo> list = petTestService.getAllPetTestList();
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "测试分页查询接口")
    @GetMapping("/page")
    public PageResult<PetTestPageVo> getPageList(@ParameterObject PetTestPageQuery petTestPageQuery){
        // 调用service查询分页结果
        IPage<PetTestPageVo> petTestPageList = petTestService.getPetTestPageList(petTestPageQuery);
        // 封装分页结果
        return PageResult.success(petTestPageList);
    }
}
