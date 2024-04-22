package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetPost;
import com.zcy.pet.model.form.PermForm;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.query.PetPermPageQuery;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.service.PetPermissionService;
import com.zcy.pet.service.PetPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "权限模块", description = "权限模块接口")
@Slf4j
@RestController
@RequestMapping("/api/perm")
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

    @Operation(description = "分页查询所有权限接口")
    @GetMapping("/page")
    public PageResult<PetPermissionVo> getAllPermsPage(@ParameterObject PetPermPageQuery petPermPageQuery) {
        //  调用service查询所有结果
        IPage<PetPermissionVo> petRolePageList = petPermissionService.getPetPermPageList(petPermPageQuery);
        return PageResult.success(petRolePageList);
    }

    @Operation(summary = "新增权限")
    @PostMapping
    public Result addPerm(@Valid @RequestBody PermForm permForm) {
        boolean result = petPermissionService.savePerm(permForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改权限")
    @PutMapping(value = "/{pid}")
    public Result updatePerm(@Parameter(description = "用户ID") @PathVariable Long pid, @Valid @RequestBody PermForm permForm) {
        boolean result = petPermissionService.updatePerm(pid, permForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除权限")
    @DeleteMapping("/{ids}")
    public Result deletePerms(@Parameter(description = "删除角色，多个以英文逗号(,)拼接") @PathVariable String ids) {
        boolean result = petPermissionService.deletePerms(ids);
        return Result.judge(result);
    }
}
