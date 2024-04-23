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
        if (pid == null || CollectionUtil.isEmpty(mids)) {
            return false;
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
}
