package com.zcy.pet.controller;

import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetUserVo;
import com.zcy.pet.model.vo.UserToken;
import com.zcy.pet.service.PetUserService;
import com.zcy.pet.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户模块", description = "用户模块接口")
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class PetUserController {
    private final PetUserService petUserService;
    private final PetUserConverter petUserConverter;
    private final JwtTokenUtil jwtTokenUtil;
    @Operation(description = "分页查询所有用户接口")
    @GetMapping("/{pageSize}/{pageNo}")
    // TODO: 2024/4/15  可添加分页查询条件
    public Result<PageResult<PetUserVo>> getAllUsersPage(@PathVariable Integer pageSize, @PathVariable Integer pageNo) {
        //  调用service查询所有结果
        PageResult<PetUserVo> allPetUserPage = petUserService.getAllPetUserPage(pageSize, pageNo);
        // 封装结果
        return Result.success(allPetUserPage);
    }

    @Operation(description = "查询所有用户接口")
    @GetMapping
    public Result<List<PetUserVo>> getAllUsers() {
        //  调用service查询所有结果
        List<PetUserVo> list = petUserService.getAllPetUser();
        // 封装结果
        return Result.success(list);
    }

    @Operation(description = "根据id查询用户")
    @GetMapping("/{id}")
    public Result<PetUserVo> getUserById(@PathVariable Integer id) {
        // TODO: 2024/4/15  此处应用jwt解出userid
        //  调用service查询所有结果
        PetUserVo UserVo = petUserService.getUserById(id);
        // 封装结果
        return Result.success(UserVo);
    }

    @Operation(description = "根据id修改用户")
    @PutMapping("/updateUser")
    public Result<PetUserVo> updateUser(@RequestBody PetUser petUser) {
        //  调用service查询所有结果
        boolean flag = petUserService.updateUserById(petUser);
        // 封装结果
        if (flag) {
            return Result.success(petUserConverter.entityToVo(petUser));
        }
        return Result.failed("修改失败");
    }

    @Operation(description = "根据id修改用户")
    @DeleteMapping("/{id}")
    public Result<Boolean> updateUser(@PathVariable Integer id) {
        // TODO: 2024/4/15  此处应用jwt解出userid
        //  调用service查询所有结果
        boolean flag = petUserService.removeById(id);
        // 封装结果
        if (flag) {
            return Result.success(null, "删除成功");
        }
        return Result.failed("修改失败");
    }

}
