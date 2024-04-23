package com.zcy.pet.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetPermMenuMapper;
import com.zcy.pet.model.entity.PetPermMenu;
import com.zcy.pet.model.entity.PetRolePermission;
import com.zcy.pet.service.PetPermMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetPermMenuServiceImpl extends ServiceImpl<PetPermMenuMapper, PetPermMenu> implements PetPermMenuService {
    @Override
    public boolean savePermMenus(Long pid, List<Long> mids) {
        if (pid == null) {
            return false;
        }

        if (CollectionUtil.isEmpty(mids)) {
            // pids 为空数组,证明该角色没有任何菜单,需要删除权限关联的菜单信息
            this.remove(new LambdaQueryWrapper<PetPermMenu>()
                    .eq(PetPermMenu::getPid, pid));
            return true;
        }

        // 原权限菜单ID集合
        List<Long> menuIds = this.list(new LambdaQueryWrapper<PetPermMenu>()
                        .eq(PetPermMenu::getPid, pid))
                .stream()
                .map(PetPermMenu::getMid)
                .toList();

        // 新增角色权限
        List<Long> saveMenuIds;
        if (CollectionUtil.isEmpty(menuIds)) {
            saveMenuIds = mids;
        } else {
            saveMenuIds = mids.stream()
                    .filter(mid -> !menuIds.contains(mid))
                    .collect(Collectors.toList());
        }

        List<PetPermMenu> saveMenus = saveMenuIds
                .stream()
                .map(mid -> new PetPermMenu(null, pid, mid))
                .toList();
        this.saveBatch(saveMenus);

        // 删除角色权限
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<Long> removeMenuIds = menuIds.stream()
                    .filter(mid -> !mids.contains(mid))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removeMenuIds)) {
                this.remove(new LambdaQueryWrapper<PetPermMenu>()
                        .eq(PetPermMenu::getPid, pid)
                        .in(PetPermMenu::getMid, removeMenuIds)
                );
            }
        }
        return true;
    }

    @Override
    public boolean hasAssignedPerms(Long mid) {
        int count = this.baseMapper.countMenusForPerm(mid);
        return count > 0;
    }
}
