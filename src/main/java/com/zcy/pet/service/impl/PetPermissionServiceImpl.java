package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetPermissionConverter;
import com.zcy.pet.mapper.PetPermissionMapper;
import com.zcy.pet.model.bo.PetPermissionBo;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.PermForm;
import com.zcy.pet.model.query.PetPermPageQuery;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.service.PetPermMenuService;
import com.zcy.pet.service.PetPermissionService;
import com.zcy.pet.service.PetRolePermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PetPermissionServiceImpl extends ServiceImpl<PetPermissionMapper, PetPermission> implements PetPermissionService {
    private final PetPermissionConverter petPermissionConverter;

    private final PetPermMenuService petPermMenuService;
    private final PetRolePermissionService petRolePermissionService;

    @Override
    public List<PetPermissionVo> getAllPetPermissionList() {
        List<PetPermission> petPermissionList = this.baseMapper.selectList(null);
        return petPermissionConverter.entityToVo(petPermissionList);
    }

    @Override
    public IPage<PetPermissionVo> getPetPermPageList(PetPermPageQuery petPermPageQuery) {
        // 构造参数
        Integer pageNum = petPermPageQuery.getPageNum();
        Integer pageSize = petPermPageQuery.getPageSize();
        Page<PetPermissionBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petPermPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetPermissionBo> petPermissionBo = this.baseMapper.getPagePetPermList(pageQuery, petPermPageQuery);
        // 数据转换
        return petPermissionConverter.boToPageVo(petPermissionBo);
    }

    @Override
    public boolean savePerm(PermForm permForm) {
        Long permId = permForm.getPid();
        String permCode = permForm.getPermCode();
        long count = this.count(new LambdaQueryWrapper<PetPermission>()
                .ne(permId != null, PetPermission::getPid, permId)
                .and(
                        wrapper -> wrapper.eq(PetPermission::getPermCode, permCode)
                                .or()
                                .eq(PetPermission::getPermName, permForm.getPermName())
                ));
        Assert.isTrue(count == 0, "权限名称或权限编码已存在，请修改后重试！");

        // 实体转换
        PetPermission perm = petPermissionConverter.form2Entity(permForm);

        boolean result = this.save(perm);
        if (result) {
            // 添加菜单信息
            petPermMenuService.savePermMenus(perm.getPid(), permForm.getMids());
        }
        return result;
    }

    @Override
    public boolean updatePerm(Long pid, PermForm permForm) {
        // 编辑权限时，判断权限是否存在
        PetPermission oldPerm = null;
        if (pid != null) {
            oldPerm = this.getById(pid);
            Assert.isTrue(oldPerm != null, "权限不存在");
        }
        permForm.setPid(pid);
        String permCode = permForm.getPermCode();
        long count = this.count(new LambdaQueryWrapper<PetPermission>()
                .ne(pid != null, PetPermission::getPid, pid)
                .and(
                        wrapper -> wrapper.eq(PetPermission::getPermCode, permCode)
                                .or()
                                .eq(PetPermission::getPermName, permForm.getPermName())
                ));
        Assert.isTrue(count == 0, "权限名称或权限编码已存在，请修改后重试！");

        // 实体转换
        PetPermission perm = petPermissionConverter.form2Entity(permForm);

        boolean result = this.updateById(perm);
        if (result) {
            // 添加菜单信息
            petPermMenuService.savePermMenus(perm.getPid(), permForm.getMids());
        }
        return result;
    }

    @Override
    public boolean deletePerms(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的权限ID不能为空");
        // 获取需要删除的权限ID列表
        List<Long> pIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long pid : pIds) {
            PetPermission perm = this.getById(pid);
            Assert.isTrue(perm != null, "权限不存在");

            // 判断权限是否被角色关联
            boolean isRoleAssigned = petRolePermissionService.hasAssignedRoles(pid);
            Assert.isTrue(!isRoleAssigned, "权限【{}】已分配给角色，请先解除关联后删除", perm.getPermName());

            boolean deleteResult = this.removeById(pid);
            if (deleteResult) {
                // TODO: 删除成功,删除权限关联角色信息
            }
        }
        return true;
    }

    @Override
    public List<Option> listPermOptions() {
        // 查询数据
        List<PetPermission> permList = this.list(new LambdaQueryWrapper<PetPermission>()
                .select(PetPermission::getPid, PetPermission::getPermName)
        );
        log.info("权限列表:{}", permList);
        // 实体转换
        return petPermissionConverter.entities2Options(permList);
    }


}
