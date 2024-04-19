package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.service.PetPermissionService;
import com.zcy.pet.service.PetPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "权限模块", description = "权限模块接口")
@Slf4j
@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
public class PetPermissionController {
    private final PetPermissionService petPermissionService;

    @Operation(description = "测试查询所有接口")
    @GetMapping
    public Result<List<PetPermissionVo>> getAllPermissions(){
        //  调用service查询所有结果
        List<PetPermissionVo> list = petPermissionService.getAllPetPermissionList();
        // 封装结果
        return Result.success(list);
    }
}
