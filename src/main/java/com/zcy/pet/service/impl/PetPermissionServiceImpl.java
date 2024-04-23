package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PetPermissionServiceImpl extends ServiceImpl<PetPermissionMapper, PetPermission> implements PetPermissionService {
    private final PetPermissionConverter petPermissionConverter;

    private final PetPermMenuService petPermMenuService;

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
        Assert.isTrue(count == 0, "菜单名称或菜单编码已存在，请修改后重试！");

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
        return false;
    }

    @Override
    public boolean deletePerms(String ids) {
        return false;
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
