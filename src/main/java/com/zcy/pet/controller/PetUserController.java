package com.zcy.pet.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetUserRole;
import com.zcy.pet.model.form.UserForm;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.*;
import com.zcy.pet.service.PetPermissionService;
import com.zcy.pet.service.PetRoleService;
import com.zcy.pet.service.PetUserRoleService;
import com.zcy.pet.service.PetUserService;
import com.zcy.pet.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "用户模块", description = "用户模块接口")
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class PetUserController {

    private final PetUserService petUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PetPermissionService petPermissionService;
    private final PetRoleService petRoleService;
    private final PetUserRoleService petUserRoleService;

    @Operation(description = "验证用户身份信息")
    @GetMapping("/identity/{type}")
    public Result<HashMap<String, Object>> getUserIdentity(@Parameter(description = "用户ID") @PathVariable Integer type,HttpServletRequest request) {
        //  根据token获取用户ID
        String header = request.getHeader(jwtTokenUtil.header);
        String token = header.replace("Bearer", "");
        UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(token);
        Long uid = userInfoToken.getUid();
        //  根据用户ID验证用户身份
        HashMap<String, Object> resultData = petUserService.verifyUserIdentity(uid,type);
        return Result.success(resultData);
    }

    @Operation(description = "分页查询所有用户接口")
    @GetMapping("/page")
    public PageResult<PetUserPageVo> getAllUsersPage(@ParameterObject PetUserPageQuery petUserPageQuery) {
        //  调用service查询所有结果
        IPage<PetUserPageVo> petUserPageList = petUserService.getPetUserPageList(petUserPageQuery);
        return PageResult.success(petUserPageList);
    }

    @Operation(description = "查询所有用户接口")
    @GetMapping
    public Result<List<PetUserVo>> getAllUsers() {
        List<PetUserVo> list = petUserService.getAllPetUser();
        return Result.success(list);
    }

    @Operation(description = "查询用户登录信息")
    @GetMapping("/info")
    public Result<PetUserInfoVo> getUserInfoById(HttpServletRequest request) {
        String header = request.getHeader(jwtTokenUtil.header);
        // 截取token
        String token = header.replace("Bearer", "");
        UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(token);
        Long uid = userInfoToken.getUid();
        log.info("token: {}", token);
        //zf TODO:  需要根据用户ID查询这个用户的信息以及角色和权限编码数组 格式在PetUserInfoVo中定义
        PetUserInfoVo userInfoVo = petUserService.getUserInfoById(uid);
        List<String> RoleList = petUserRoleService.getUserRoleListByUserId(uid);
        HashMap<String, List<String>> roleMapRerms = new HashMap<>();
        for (String perm : RoleList) {
            LambdaQueryWrapper<PetUserRole> wrapper = new LambdaQueryWrapper<>();
            List<String> collect = petRoleService.list(new LambdaQueryWrapper<PetRole>().eq(PetRole::getRid, perm)).stream().map(item -> item.getRoleCode()).collect(Collectors.toList());
            roleMapRerms.put(perm, collect);
        }
        userInfoVo.setRoleMapPerms(roleMapRerms);
        return Result.success(userInfoVo);
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public Result saveUser(@RequestBody @Valid UserForm userForm) {
        boolean result = petUserService.saveUser(userForm);
        return Result.judge(result);
    }

    @Operation(description = "修改用户")
    @PutMapping("/{userId}")
    public Result<PetUserVo> updateUser(@Parameter(description = "用户ID") @PathVariable Long userId, @RequestBody @Validated UserForm userForm) {
        boolean result = petUserService.updateUser(userId, userForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{ids}")
    public Result deleteUsers(@Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = petUserService.deleteUsers(ids);
        return Result.judge(result);
    }

    @Operation(summary = "用户下拉列表")
    @GetMapping("/options")
    public Result<List<Option>> listModelOptions() {
        List<Option> list = petUserService.listUserOptions();
        return Result.success(list);
    }

    @Operation(summary = "更新用户配置信息")
    @PutMapping("/config/{uid}")
    public Result<PetUserVo> updateUserConfig(@Parameter(description = "用户ID") @PathVariable Long uid, @RequestBody @Validated JSONObject config) {
        // zf TODO: 这里查询的时候为什么config字段为null,SQL日志看的查询出来的有 <<BLOB>>
        boolean result  =  petUserService.updateUserConfig(uid,config);
        return Result.judge(result);
    }
    @Operation(summary = "获取用户配置信息")
    @GetMapping("/config")
    public Result<JSONObject> getUserConfig(HttpServletRequest request) {
        String header = request.getHeader(jwtTokenUtil.header);
        // 截取token
        String token = header.replace("Bearer", "");
        UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(token);
        Long uid = userInfoToken.getUid();
        JSONObject result  =  petUserService.getUserConfig(uid);
        return Result.success(result);
    }

    @Operation(summary = "获取用户个人信息")
    @GetMapping("/profile")
    public Result<PetUserProfileVo> getUserProfile(HttpServletRequest request) {
        String header = request.getHeader(jwtTokenUtil.header);
        // 截取token
        String token = header.replace("Bearer", "");
        UserTokenInfo userInfoToken = jwtTokenUtil.getUserInfoToken(token);
        Long uid = userInfoToken.getUid();
        PetUserProfileVo result  =  petUserService.getUserProfile(uid);
        return Result.success(result);
    }
}
