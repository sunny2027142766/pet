package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetRolePermission;
import com.zcy.pet.model.entity.PetUserRole;

import java.util.List;

public interface PetRolePermissionService extends IService<PetRolePermission> {

    /**
     * 保存角色权限
     * @param roleId 角色ID
     * @param pids 权限ID集合
     * @return boolean
     */
    boolean saveRolePerms(Long roleId, List<Long> pids);

    /**
     * 判断权限是否存在绑定的角色
     * @param pid 角色ID
     * @return boolean
     */
    boolean hasAssignedRoles(Long pid);
}
