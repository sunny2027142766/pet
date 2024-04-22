package com.zcy.pet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.service.PetRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<PetRoleVo>> getAllRoles() {
        //  调用service查询所有结果
        List<PetRoleVo> list = petRoleService.getAllPetRole();
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "分页查询所有角色接口")
    @GetMapping("/page")
    public PageResult<PetRolePageVo> getAllRolesPage(@ParameterObject PetRolePageQuery petRolePageQuery) {
        IPage<PetRolePageVo> petRolePageList = petRoleService.getPetRolePageList(petRolePageQuery);
        return PageResult.success(petRolePageList);
    }

    @Operation(summary = "角色下拉列表")
    @GetMapping("/options")
    public Result<List<Option>> listRoleOptions() {
        List<Option> list = petRoleService.listRoleOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增角色")
    @PostMapping
    public Result addRole(@Valid @RequestBody RoleForm roleForm) {
        boolean result = petRoleService.saveRole(roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改角色")
    @PutMapping(value = "/{rid}")
    public Result updateRole(@Parameter(description = "用户ID") @PathVariable Long rid, @Valid @RequestBody RoleForm roleForm) {
        boolean result = petRoleService.updateRole(rid, roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{ids}")
    public Result deleteRoles(@Parameter(description = "删除角色，多个以英文逗号(,)拼接") @PathVariable String ids) {
        boolean result = petRoleService.deleteRoles(ids);
        return Result.judge(result);
    }
}
