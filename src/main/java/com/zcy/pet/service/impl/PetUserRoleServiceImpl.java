package com.zcy.pet.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetRoleMapper;
import com.zcy.pet.mapper.PetUserRoleMapper;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.entity.PetUserRole;
import com.zcy.pet.service.PetUserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetUserRoleServiceImpl extends ServiceImpl<PetUserRoleMapper, PetUserRole> implements PetUserRoleService {

    @Autowired
    private PetRoleMapper petRoleMapper;

    @Override
    public boolean saveUserRoles(Long uid, List<Long> rids) {
        if (uid == null || CollectionUtil.isEmpty(rids)) {
            return false;
        }

        // 用户原角色ID集合
        List<Long> userRoleIds = this.list(new LambdaQueryWrapper<PetUserRole>()
                        .eq(PetUserRole::getUid, uid))
                .stream()
                .map(PetUserRole::getRid)
                .collect(Collectors.toList());

        // 新增用户角色
        List<Long> saveRoleIds;
        if (CollectionUtil.isEmpty(userRoleIds)) {
            saveRoleIds = rids;
        } else {
            saveRoleIds = rids.stream()
                    .filter(rid -> !userRoleIds.contains(rid))
                    .collect(Collectors.toList());
        }

        List<PetUserRole> saveUserRoles = saveRoleIds
                .stream()
                .map(rid -> new PetUserRole(null, uid, rid))
                .toList();
        this.saveBatch(saveUserRoles);

        // 删除用户角色
        if (CollectionUtil.isNotEmpty(userRoleIds)) {
            List<Long> removeRoleIds = userRoleIds.stream()
                    .filter(roleId -> !rids.contains(roleId))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removeRoleIds)) {
                this.remove(new LambdaQueryWrapper<PetUserRole>()
                        .eq(PetUserRole::getUid, uid)
                        .in(PetUserRole::getRid, removeRoleIds)
                );
            }
        }
        return true;
    }

    @Override
    public boolean hasAssignedUsers(Long roleId) {
        int count = this.baseMapper.countUsersForRole(roleId);
        return count > 0;
    }

    @Override
    public List<String> getUserRoleListByUserId(Long uid) {
        LambdaQueryWrapper<PetUserRole> query = new LambdaQueryWrapper<>();
        query.eq(PetUserRole::getUid, uid);
        List<PetUserRole> list = this.list(query);
        List<String> roleList = list.stream().map(PetUserRole::getRid).map(String::valueOf).collect(Collectors.toList());
        return roleList;
    }

    @Override
    public List<String> getUserRoleCodesByUserId(Long uid) {
        LambdaQueryWrapper<PetUserRole> query = new LambdaQueryWrapper<>();
        query.eq(PetUserRole::getUid, uid);
        List<PetUserRole> userRoles  = this.list(query);

        List<String> roleCodes = new ArrayList<>();
        for (PetUserRole userRole : userRoles) {
            // 根据角色ID查询角色信息
            PetRole role = petRoleMapper.selectById(userRole.getRid());
            if (role != null) {
                roleCodes.add(role.getRoleCode());
            }
        }

        return roleCodes;
    }

}
