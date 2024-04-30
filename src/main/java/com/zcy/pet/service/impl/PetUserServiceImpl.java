package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.mapper.*;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.*;
import com.zcy.pet.model.form.UserForm;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.*;
import com.zcy.pet.service.PetUserRoleService;
import com.zcy.pet.service.PetUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PetUserServiceImpl extends ServiceImpl<PetUserMapper, PetUser> implements PetUserService {

    private final PetUserConverter petUserConverter;

    private final PetUserRoleService petUserRoleService;

    @Autowired
    private PetUserRoleMapper petUserRoleMapper;

    @Autowired
    private PetRoleMapper petRoleMapper;

    @Autowired
    private PetRolePermissionMapper petRolePermissionMapper;

    @Autowired
    private PetPermissionMapper petPermissionMapper;

    @Autowired
    private PetPermMenuMapper permMenuMapper;

    @Autowired
    private PetMenuMapper petMenuMapper;

    @Autowired
    private PetIconMapper petIconMapper;

    @Autowired
    private PetPostMapper petPostMapper; // 查询发帖数

    @Autowired
    private PostCommentMapper postCommentMapper; // 查询评论数

    @Autowired
    private PostLikeMapper postLikeMapper; // 查询点赞数

    @Autowired
    private PostShareMapper postShareMapper; // 查询分享数


    /**
     * 用户管理分页查询
     *
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
     *
     * @return List<PetUserVo>
     */
    @Override
    public List<PetUserVo> getAllPetUser() {
        List<PetUser> petUsers = this.baseMapper.getAllPetUser();
        return petUserConverter.entityToVo(petUsers);
    }

    /**
     * 根据邮箱获取用户信息
     *
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
     *
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
     *
     * @param userId   用户ID
     * @param userForm 用户表单对象
     * @return boolean
     */
    @Override
    public boolean updateUser(Long userId, UserForm userForm) {
        String email = userForm.getEmail();
        userForm.setUid(userId);
        log.info("执行修改用户信息 userId: {}, userForm: {}", userId, userForm);
        long count = this.count(new LambdaQueryWrapper<PetUser>().eq(PetUser::getEmail, email).ne(PetUser::getUid, userId));

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
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return boolean
     */
    @Override
    public boolean deleteUsers(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");
        // zf TODO: 删除时关联的角色信息删除
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 根据用户ID获取用户登录信息
     *
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
                        PetUser::getNickname,
                        PetUser::getConfig
                ));
        // entity->VO
        PetUserInfoVo userInfoVO = petUserConverter.toUserInfoVo(user);

        // 查询用户关联的角色信息
        LambdaQueryWrapper<PetUserRole> userRoleWrapper = Wrappers.lambdaQuery();
        userRoleWrapper.eq(PetUserRole::getUid, userId);
        List<PetUserRole> userRoles = petUserRoleMapper.selectList(userRoleWrapper);
        // 获取用户角色code数组
        List<String> roleCodes = new ArrayList<>();
        for (PetUserRole userRole : userRoles) {
            // 根据角色ID查询角色信息
            PetRole role = petRoleMapper.selectById(userRole.getRid());
            if (role != null) {
                roleCodes.add(role.getRoleCode());
            }
        }

        // 查询角色关联的权限信息
        List<Long> roleIds = userRoles.stream().map(PetUserRole::getRid).collect(Collectors.toList());
        LambdaQueryWrapper<PetRolePermission> rolePermWrapper = Wrappers.lambdaQuery();
        rolePermWrapper.in(PetRolePermission::getRid, roleIds);
        List<PetRolePermission> rolePerms = petRolePermissionMapper.selectList(rolePermWrapper);
        // 获取用户权限code数组
        List<String> permCodes = new ArrayList<>();
        for (PetRolePermission rolePerm : rolePerms) {
            // 根据角色ID查询权限信息
            PetPermission perm = petPermissionMapper.selectById(rolePerm.getPid());
            if (perm != null) {
                permCodes.add(perm.getPermCode());
            }
        }

        // 查询权限关联的菜单信息
        List<Long> permIds = rolePerms.stream()
                .map(PetRolePermission::getPid)
                .collect(Collectors.toList());
        LambdaQueryWrapper<PetPermMenu> permMenuWrapper = Wrappers.lambdaQuery();
        permMenuWrapper.in(PetPermMenu::getPid, permIds);
        List<PetPermMenu> permMenus = permMenuMapper.selectList(permMenuWrapper);
        // 获取用户菜单list
        List<PetMenuVo> menuList = new ArrayList<>();
        for (PetPermMenu permMenu : permMenus) {
            // 根据角色ID查询权限信息
            PetMenu menu = petMenuMapper.selectById(permMenu.getMid());
            PetIcon petIcon = petIconMapper.selectById(menu.getIcon());
            PetMenuVo menuVo = new PetMenuVo();
            menuVo.setMid(menu.getMid());
            menuVo.setPid(permMenu.getPid());
            menuVo.setTitle(menu.getTitle());
            menuVo.setIcon(petIcon.getPath());
            menuVo.setPath(menu.getPath());
            menuVo.setLevel(menu.getLevel());
            menuVo.setSort(menu.getSort());
            menuVo.setIsFront(menu.getIsFront());
            if (menu != null) {
                menuList.add(menuVo);
            }
        }

        // 给VO对象添加查询的内容
        userInfoVO.setRoles(roleCodes);
        userInfoVO.setPerms(permCodes);
        userInfoVO.setMenus(menuList);

        //  TODO: 获取用户角色集合 对应查询出来数据库中 role_code 数组
//        List<String> roles1 = petUserRoleService.getUserRoleCodesByUserId(userId);
//        userInfoVO.setRoles(roles1);

        return userInfoVO;
    }

    @Override
    public List<Option> listUserOptions() {
        // 查询数据
        List<PetUser> userList = this.list(new LambdaQueryWrapper<PetUser>().select(PetUser::getUid, PetUser::getUsername));
        log.info("模型列表:{}", userList);
        // 实体转换
        return petUserConverter.entities2Options(userList);
    }

    /**
     * 验证用户身份信息
     *
     * @param uid 用户ID
     * @return HashMap<String, Object>
     */
    @Override
    public HashMap<String, Object> verifyUserIdentity(Long uid, int type) {
        // TODO: 暂时pass,似乎要在前端进行判断
        // 1. 先查询用户是否在数据库中
        long count = this.count(new LambdaQueryWrapper<PetUser>().eq(PetUser::getUid, uid));
        Assert.isTrue(count == 0, "该用户不存在已存在");
        // 2.根据type判断用户的登录类型
        if (type == 1) { // 前台登录
            // 2.1 验证用户是否具有
        }
        return null;
    }

    @Override
    public boolean updateUserConfig(Long uid, JSONObject config) {
        // 更新用户的config字段
        PetUser petUser = new PetUser();
        petUser.setConfig(config);
        return this.update(petUser, new LambdaQueryWrapper<PetUser>().eq(PetUser::getUid, uid));
    }

    @Override
    public JSONObject getUserConfig(Long uid) {
        PetUser petUser = this.getOne(new LambdaQueryWrapper<PetUser>().eq(PetUser::getUid, uid)
                .select(PetUser::getConfig));
        if (petUser == null) {
            return null;
        }
        return petUser.getConfig();
    }

    @Override
    public PetUserProfileVo getUserProfile(Long uid) {
        // 查询用户基本信息
        PetUser petUser = this.getOne(new LambdaQueryWrapper<PetUser>().eq(PetUser::getUid, uid));
        // 查询用户发帖数
        Long postNum = petPostMapper.selectCount(new LambdaQueryWrapper<PetPost>().eq(PetPost::getUid, uid));
        // 查询用户点赞数
        Long likeNum = postLikeMapper.selectCount(new LambdaQueryWrapper<PetPostLike>().eq(PetPostLike::getUid, uid));
        // 查询用户分享数
        Long shareNum = postShareMapper.selectCount(new LambdaQueryWrapper<PetPostShare>().eq(PetPostShare::getUid, uid));
        // 查询用户评论数
        Long commentNum = postCommentMapper.selectCount(new LambdaQueryWrapper<PetPostComment>().eq(PetPostComment::getUid, uid));
        // 构造结果
        PetUserProfileVo userProfileVo = new PetUserProfileVo();
        userProfileVo.setUid(petUser.getUid());
        userProfileVo.setUsername(petUser.getUsername());
        userProfileVo.setNickname(petUser.getNickname());
        userProfileVo.setEmail(petUser.getEmail());
        userProfileVo.setPhone(petUser.getPhone());
        userProfileVo.setBirthday(petUser.getBirthday());
        userProfileVo.setAvatar(petUser.getAvatar());
        userProfileVo.setAddress(petUser.getAddress());
        userProfileVo.setSex(petUser.getSex());
        userProfileVo.setPostNum(postNum);
        userProfileVo.setLikeNum(likeNum);
        userProfileVo.setShareNum(shareNum);
        userProfileVo.setCommentNum(commentNum);
        return userProfileVo;
    }

}
