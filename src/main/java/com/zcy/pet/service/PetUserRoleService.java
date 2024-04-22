package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetUserRole;

import java.util.List;

public interface PetUserRoleService extends IService<PetUserRole> {
    /**
     * 保存用户角色
     *
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     * @return boolean
     */
    boolean saveUserRoles(Long userId, List<Long> roleIds);

    /**
     * 判断角色是否存在绑定的用户
     * @param roleId 角色ID
     * @return boolean
     */
    boolean hasAssignedUsers(Long roleId);

    List<String> getUserRoleListByUserId(Long uid);
}
