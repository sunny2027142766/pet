package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetPermMenu;

import java.util.List;

public interface PetPermMenuService extends IService<PetPermMenu> {

    /**
     * 保存权限菜单关系
     * @param pid 权限ID
     * @param mids 菜单ID集合
     * @return boolean
     */
    boolean savePermMenus(Long pid, List<Long> mids);

    /**
     * 判断菜单是否分配给权限
     * @param mid 菜单ID
     * @return boolean
     */
    boolean hasAssignedPerms(Long mid);
}
