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
        if (roleId == null || CollectionUtil.isEmpty(pids)) {
            return false;
        }

        // 原角色权限ID集合
        List<Long> userPermIds = this.list(new LambdaQueryWrapper<PetRolePermission>()
                        .eq(PetRolePermission::getRid, roleId))
                        .stream()
                        .map(PetRolePermission::getRid)
                        .toList();

        // 新增角色权限
        List<Long> savePermIds;
        if (CollectionUtil.isEmpty(userPermIds)) {
            savePermIds = pids;
        } else {
            savePermIds = pids.stream()
                    .filter(rid -> !userPermIds.contains(rid))
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
}
