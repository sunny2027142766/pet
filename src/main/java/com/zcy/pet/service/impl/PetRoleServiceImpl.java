package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetRoleConverter;
import com.zcy.pet.mapper.PetRoleMapper;
import com.zcy.pet.model.bo.PetRoleBo;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetRoleVo;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.service.PetRolePermissionService;
import com.zcy.pet.service.PetRoleService;
import com.zcy.pet.service.PetUserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PetRoleServiceImpl extends ServiceImpl<PetRoleMapper, PetRole> implements PetRoleService {

    private final PetRoleConverter petRoleConverter;

    private final PetRolePermissionService petRolePermissionService;
    private final PetUserRoleService petUserRoleService;

    /**
     * 获取所有角色信息
     *
     * @return List<PetRoleVo>
     */
    @Override
    public List<PetRoleVo> getAllPetRole() {
        List<PetRole> petRoles = this.baseMapper.getAllPetRole();
        return petRoleConverter.entityToVo(petRoles);
    }

    /**
     * 角色分页查询
     *
     * @param petRolePageQuery 角色分页查询参数
     * @return IPage<PetRolePageVo>
     */
    @Override
    public IPage<PetRolePageVo> getPetRolePageList(PetRolePageQuery petRolePageQuery) {
        log.info("角色查询参数:{}", petRolePageQuery);
        // 构造参数
        Integer pageNum = petRolePageQuery.getPageNum();
        Integer pageSize = petRolePageQuery.getPageSize();
        Page<PetRoleBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petRolePageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetRoleBo> petRoleBo = this.baseMapper.getPagePetRoleList(pageQuery, petRolePageQuery);
        // 数据转换
        return petRoleConverter.boToPageVo(petRoleBo);
    }

    /**
     * 获取角色下拉框选项
     *
     * @return List<Option>
     */
    @Override
    public List<Option> listRoleOptions() {
        // 查询数据
        List<PetRole> roleList = this.list(new LambdaQueryWrapper<PetRole>()
                .select(PetRole::getRid, PetRole::getRoleName)
        );
        log.info("角色列表:{}", roleList);
        // 实体转换
        return petRoleConverter.entities2Options(roleList);
    }

    /**
     * 添加角色
     * @param roleForm 角色表单对象
     * @return boolean
     */
    @Override
    public boolean saveRole(RoleForm roleForm) {
        Long roleId = roleForm.getRid();
        String roleCode = roleForm.getRoleCode();
        long count = this.count(new LambdaQueryWrapper<PetRole>()
                .ne(roleId != null, PetRole::getRid, roleId)
                .and(
                        wrapper -> wrapper.eq(PetRole::getRoleCode, roleCode)
                                .or()
                                .eq(PetRole::getRoleName, roleForm.getRoleName())
                ));
        Assert.isTrue(count == 0, "角色名称或角色编码已存在，请修改后重试！");

        // 实体转换
        PetRole role = petRoleConverter.form2Entity(roleForm);

        boolean result = this.save(role);
        if (result) {
            // 添加权限信息
            petRolePermissionService.saveRolePerms(role.getRid(), roleForm.getPids());
        }
        return result;
    }

    /**
     * 修改角色
     * @param roleId 角色ID
     * @param roleForm 角色表单对象
     * @return boolean
     */
    @Override
    public boolean updateRole(Long roleId, RoleForm roleForm) {
        // 编辑角色时，判断角色是否存在
        PetRole oldRole = null;
        if (roleId != null) {
            oldRole = this.getById(roleId);
            Assert.isTrue(oldRole != null, "角色不存在");
        }
        roleForm.setRid(roleId);
        String roleCode = roleForm.getRoleCode();
        long count = this.count(new LambdaQueryWrapper<PetRole>()
                .ne(roleId != null, PetRole::getRid, roleId)
                .and(
                        wrapper -> wrapper.eq(PetRole::getRoleCode, roleCode)
                                .or()
                                .eq(PetRole::getRoleName, roleForm.getRoleName())
                ));
        Assert.isTrue(count == 0, "角色名称或角色编码已存在，请修改后重试！");

        // 实体转换
        PetRole role = petRoleConverter.form2Entity(roleForm);

        boolean result = this.updateById(role);
        if (result) {
            // zf TODO: 更新时候应该先删除原有权限，在添加新的权限
            // 添加权限信息
            petRolePermissionService.saveRolePerms(role.getRid(), roleForm.getPids());
        }
        return result;
    }

    /**
     * 删除角色
     * @param ids 角色ID，多个使用英文逗号(,)分割
     * @return boolean
     */
    @Override
    public boolean deleteRoles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的角色ID不能为空");
        List<Long> roleIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long roleId : roleIds) {
            PetRole role = this.getById(roleId);
            Assert.isTrue(role != null, "角色不存在");

            // 判断角色是否被用户关联
            boolean isRoleAssigned = petUserRoleService.hasAssignedUsers(roleId);
            Assert.isTrue(!isRoleAssigned, "角色【{}】已分配用户，请先解除关联后删除", role.getRoleName());

            boolean deleteResult = this.removeById(roleId);
            if (deleteResult) {
                // zf TODO: 删除成功,删除权限
//                petRolePermissionService.deleteRolePerms(roleId);
            }
        }
        return true;
    }



}
