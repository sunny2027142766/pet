package com.zcy.pet;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetUserRole;
import com.zcy.pet.model.vo.PetUserInfoVo;
import com.zcy.pet.service.PetPermissionService;
import com.zcy.pet.service.PetRoleService;
import com.zcy.pet.service.PetUserRoleService;
import com.zcy.pet.service.PetUserService;
import com.zcy.pet.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest

class PetApplicationTests {
    @Autowired
    PetUserService petUserService;
    @Autowired
   JwtTokenUtil jwtTokenUtil;
    @Autowired
    PetPermissionService petPermissionService;
    @Autowired
    PetRoleService petRoleService;
    @Autowired
    PetUserRoleService petUserRoleService;

    @Test
    public void test01() {
        Long uid = 1l;
        //zf TODO:  需要根据用户ID查询这个用户的信息以及角色和权限编码数组 格式在PetUserInfoVo中定义
        PetUserInfoVo userInfoVo = petUserService.getUserInfoById(uid);
        List<String> RoleList = petUserRoleService.getUserRoleListByUserId(uid);
        userInfoVo.setRoles(RoleList);
        HashMap<String, List<String>> roleMapRerms = new HashMap<>();
        for (String perm : RoleList) {
            LambdaQueryWrapper<PetUserRole> wrapper = new LambdaQueryWrapper<>();
            List<String> collect = petRoleService.list(new LambdaQueryWrapper<PetRole>().eq(PetRole::getRid, perm)).stream().map(item -> item.getRoleCode()).collect(Collectors.toList());
            roleMapRerms.put(perm, collect);
        }
        userInfoVo.setRoleMapPerms(roleMapRerms);
        System.err.println(userInfoVo);
    }
}
