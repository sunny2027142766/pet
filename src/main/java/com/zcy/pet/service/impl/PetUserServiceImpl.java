package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.mapper.PetUserMapper;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.form.UserForm;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.PetUserInfoVo;
import com.zcy.pet.model.vo.PetUserPageVo;
import com.zcy.pet.model.vo.PetUserVo;
import com.zcy.pet.service.PetUserRoleService;
import com.zcy.pet.service.PetUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PetUserServiceImpl extends ServiceImpl<PetUserMapper, PetUser> implements PetUserService {

    private final PetUserConverter petUserConverter;

    private final PetUserRoleService petUserRoleService;

    /**
     * 用户管理分页查询
     * @param petUserPageQuery 分页查询参数
     * @return IPage<PetUserPageVo>
     */
    @Override
    public IPage<PetUserPageVo> getPetUserPageList(PetUserPageQuery petUserPageQuery) {
        // 构造参数
        Integer pageNum = petUserPageQuery.getPageNum();
        Integer pageSize = petUserPageQuery.getPageSize();
        Page<PetUserBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petUserPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetUserBo> petUserBo = this.baseMapper.getPagePetUserList(pageQuery, petUserPageQuery);
        // 数据转换
        return petUserConverter.boToPageVo(petUserBo);
    }

    /**
     * 获取所有用户信息
     * @return List<PetUserVo>
     */
    @Override
    public List<PetUserVo> getAllPetUser() {
        List<PetUser> petUsers = this.baseMapper.getAllPetUser();
        return petUserConverter.entityToVo(petUsers);
    }

    /**
     * 根据邮箱获取用户信息
     * @param email 邮箱
     * @return PetUser
     */
    @Override
    public PetUser getUserByEmail(String email) {
        QueryWrapper<PetUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return this.getOne(queryWrapper);
    }

    /**
     * 新增用户
     * @param userForm 用户表单对象
     * @return boolean
     */
    @Override
    public boolean saveUser(UserForm userForm) {
        String email = userForm.getEmail();
        long count = this.count(new LambdaQueryWrapper<PetUser>().eq(PetUser::getEmail, email));
        Assert.isTrue(count == 0, "该邮箱已存在");

        // 实体转换 form->entity
        PetUser entity = petUserConverter.form2Entity(userForm);

        // TODO: 设置密码加密
//        String defaultEncryptPwd = passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD);
//        entity.setPassword(defaultEncryptPwd);

        // 新增用户
        boolean result = this.save(entity);

        if (result) {
            // 保存用户角色
            petUserRoleService.saveUserRoles(entity.getUid(), userForm.getRoleIds());
        }
        return result;
    }

    /**
     * 修改用户
     * @param userId 用户ID
     * @param userForm 用户表单对象
     * @return boolean
     */
    @Override
    public boolean updateUser(Long userId, UserForm userForm) {
        String email = userForm.getEmail();
        userForm.setUid(userId);
        log.info("执行修改用户信息 userId: {}, userForm: {}", userId, userForm);
        long count = this.count(new LambdaQueryWrapper<PetUser>()
                .eq(PetUser::getEmail, email)
                .ne(PetUser::getUid, userId)
        );

        Assert.isTrue(count == 0, "该邮箱已存在");

        // form -> entity
        PetUser entity = petUserConverter.form2Entity(userForm);
        System.out.println("entity: " + entity);
        // 修改用户
        boolean result = this.updateById(entity);

        if (result) {
            // 保存用户角色
            petUserRoleService.saveUserRoles(entity.getUid(), userForm.getRoleIds());
        }
        return result;
    }

    /**
     * 删除用户
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return boolean
     */
    @Override
    public boolean deleteUsers(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");
        // zf TODO: 删除时关联的角色信息删除
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 根据用户ID获取用户登录信息
     * @param userId 用户ID
     * @return PetUserInfoVo
     */
    @Override
    public PetUserInfoVo getUserInfoById(Long userId) {
        // TODO: 根据ID获取登录用户基础信息
        PetUser user = this.getOne(new LambdaQueryWrapper<PetUser>()
                .eq(PetUser::getUid, userId)
                .select(
                        PetUser::getUid,
                        PetUser::getUsername,
                        PetUser::getEmail,
                        PetUser::getAvatar,
                        PetUser::getPhone,
                        PetUser::getNickName
                )
        );
        // entity->VO
        PetUserInfoVo userInfoVO = petUserConverter.toUserInfoVo(user);

        //  TODO: 获取用户角色集合 对应查询出来数据库中 role_code 数组
//        Set<String> roles = ["ADMIN","USER"];
//        userInfoVO.setRoles(roles);

        // TODO: 获取用户权限集合 对应查询出来数据库中 perm_code 数组
//        if (CollectionUtil.isNotEmpty(roles)) {
//            Set<String> perms = ["ADMIN:ALL","USER:ALL"]
//            userInfoVO.setPerms(perms);
//        }
        return userInfoVO;
    }
}
