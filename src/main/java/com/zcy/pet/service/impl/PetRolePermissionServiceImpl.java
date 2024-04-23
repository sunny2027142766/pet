package com.zcy.pet.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetRolePermissionMapper;
import com.zcy.pet.model.entity.PetRolePermission;
import com.zcy.pet.service.PetRolePermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetRolePermissionServiceImpl extends ServiceImpl<PetRolePermissionMapper, PetRolePermission> implements PetRolePermissionService {
    @Override
    public boolean saveRolePerms(Long roleId, List<Long> pids) {
        if (roleId == null) {
            return false;
        }
        if (CollectionUtil.isEmpty(pids)) {
            // pids 为空数组,证明该角色没有任何权限,需要删除角色关联的权限信息
            this.remove(new LambdaQueryWrapper<PetRolePermission>()
                    .eq(PetRolePermission::getRid, roleId));
            return true;
        }

        // 原角色权限ID集合
        List<Long> userPermIds = this.list(new LambdaQueryWrapper<PetRolePermission>()
                        .eq(PetRolePermission::getRid, roleId))
                .stream()
                .map(PetRolePermission::getPid)
                .toList();

        // 新增角色权限
        List<Long> savePermIds;
        if (CollectionUtil.isEmpty(userPermIds)) {
            savePermIds = pids;
        } else {
            savePermIds = pids.stream()
                    .filter(pid -> !userPermIds.contains(pid))
                    .collect(Collectors.toList());
        }

        List<PetRolePermission> saveUserPerms = savePermIds
                .stream()
                .map(pid -> new PetRolePermission(null, roleId, pid))
                .toList();
        this.saveBatch(saveUserPerms);

        // 删除角色权限
        if (CollectionUtil.isNotEmpty(userPermIds)) {
            List<Long> removePermIds = userPermIds.stream()
                    .filter(pid -> !pids.contains(pid))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removePermIds)) {
                this.remove(new LambdaQueryWrapper<PetRolePermission>()
                        .eq(PetRolePermission::getRid, roleId)
                        .in(PetRolePermission::getPid, removePermIds)
                );
            }
        }
        return true;
    }

    @Override
    public boolean hasAssignedRoles(Long pid) {
        int count = this.baseMapper.countRolesForPerm(pid);
        return count > 0;
    }
}
