package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.model.vo.PetUserPageVo;
import com.zcy.pet.service.PetRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "角色模块", description = "角色模块接口")
@Slf4j
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class PetRoleController {
    private final PetRoleService petRoleService;

    @Operation(description = "查询所有角色接口")
    @GetMapping
    public Result<List<PetRoleVo>> getAllRoles(){
        //  调用service查询所有结果
        List<PetRoleVo> list = petRoleService.getAllPetRole();
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "分页查询所有角色接口")
    @GetMapping("/page")
    public PageResult<PetRolePageVo> getAllRolesPage(@ParameterObject PetRolePageQuery petRolePageQuery) {
        //  调用service查询所有结果
        IPage<PetRolePageVo> petRolePageList = petRoleService.getPetRolePageList(petRolePageQuery);
        return PageResult.success(petRolePageList);
    }
}
