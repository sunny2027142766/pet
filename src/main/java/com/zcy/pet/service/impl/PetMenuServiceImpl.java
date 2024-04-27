package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetInfoConverter;
import com.zcy.pet.converter.PetMenuConverter;
import com.zcy.pet.mapper.PetMenuMapper;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.vo.PetMenuPageVo;
import com.zcy.pet.model.vo.PetMenuTreeVo;
import com.zcy.pet.model.vo.PetMenuVo;
import com.zcy.pet.service.PetMenuService;
import com.zcy.pet.service.PetPermMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PetMenuServiceImpl extends ServiceImpl<PetMenuMapper, PetMenu> implements PetMenuService {
    private final PetMenuConverter petMenuConverter;

    private final PetPermMenuService petPermMenuService;

    @Override
    public List<PetMenu> getAllPetMenuList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetMenuPageVo> getPetMenuPageList(PetMenuPageQuery petMenuPageQuery) {
        // 构造参数
        Integer pageNum = petMenuPageQuery.getPageNum();
        Integer pageSize = petMenuPageQuery.getPageSize();
        Page<PetMenuBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petMenuPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetMenuBo> petMenuBo = this.baseMapper.getPagePetMenuList(pageQuery, petMenuPageQuery);
        // 数据转换
        return petMenuConverter.boToPageVo(petMenuBo);
    }

    @Override
    public boolean saveMenu(MenuForm menuForm) {
        Long mId = menuForm.getMid();
        String path = menuForm.getPath();
        long count = this.count(new LambdaQueryWrapper<PetMenu>()
                .ne(mId != null, PetMenu::getMid, mId)
                .and(
                        wrapper -> wrapper.eq(PetMenu::getPath, path)
                ));
        Assert.isTrue(count == 0, "菜单路径已存在，请修改后重试！");
        // 实体转换
        PetMenu menu = petMenuConverter.form2Entity(menuForm);

        return this.save(menu);
    }

    @Override
    public boolean updateMenu(Long mid, MenuForm menuForm) {
        Long mId = menuForm.getMid();
        String path = menuForm.getPath();
        long count = this.count(new LambdaQueryWrapper<PetMenu>()
                .ne(mId != null, PetMenu::getMid, mId)
                .and(
                        wrapper -> wrapper.eq(PetMenu::getPath, path)
                ));
        Assert.isTrue(count == 0, "菜单路径已存在，请修改后重试！");
        menuForm.setMid(mid);
        // 实体转换
        PetMenu menu = petMenuConverter.form2Entity(menuForm);
        return this.updateById(menu);
    }

    @Override
    public boolean deleteMenus(String idStr) {
        Assert.isTrue(StrUtil.isNotBlank(idStr), "删除的菜单ID不能为空");
        // 获取需要删除的权限ID列表
        List<Long> mids = Arrays.stream(idStr.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long mid : mids) {
            PetMenu menu = this.getById(mid);
            Assert.isTrue(menu != null, "菜单不存在");

            // 判断权限是否被角色关联
            boolean isRoleAssigned = petPermMenuService.hasAssignedPerms(mid);
            Assert.isTrue(!isRoleAssigned, "菜单【{}】已分配给权限，请先解除关联后删除", menu.getTitle());

            boolean deleteResult = this.removeById(mid);
            if (deleteResult) {
                // TODO: 删除成功,删除权限关联权限信息
            }
        }
        return true;
    }

    @Override
    public List<Option> listMenuOptions() {
        // 查询数据
        List<PetMenu> petMenus = this.list(new LambdaQueryWrapper<PetMenu>()
                .select(PetMenu::getMid, PetMenu::getTitle)
        );
        log.info("菜单列表:{}", petMenus);
        // 实体转换
        return petMenuConverter.entities2Options(petMenus);
    }

    @Override
    public IPage<PetMenuTreeVo> getPetMenuTreeList(PetMenuPageQuery petMenuPageQuery) {
        // 1. 获取所有的菜单
        List<PetMenu> petMenus = this.list();
        // 2. 创建一个 map，用于存放每个父菜单对应的子菜单列表
        Map<Long, List<PetMenu>> childrenMap = new HashMap<>();
        //  3. 遍历菜单列表，将每个菜单的子菜单列表存入 map 中
        for (PetMenu petMenu : petMenus) {
            Long parentId = petMenu.getPid();
            if (parentId == null) {
                parentId = 0L;
            }
            List<PetMenu> children = childrenMap.computeIfAbsent(parentId, k -> new ArrayList<>());
            children.add(petMenu);

        }

      return null;
    }
}
