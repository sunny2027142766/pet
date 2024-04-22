package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.form.UserForm;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetUserInfoVo;
import com.zcy.pet.model.vo.PetUserPageVo;
import com.zcy.pet.model.vo.PetUserVo;

import java.util.List;

public interface PetUserService extends IService<PetUser> {
    /**
     * 用户管理分页查询
     */
    IPage<PetUserPageVo> getPetUserPageList(PetUserPageQuery petUserPageQuery);

    /**
     * 获取所有用户信息
     */
    List<PetUserVo> getAllPetUser();

    /**
     * 根据邮箱获取用户信息
     */
    PetUser getUserByEmail(String email);

    /**
     * 新增用户
     */
    boolean saveUser(UserForm userForm);

    /**
     * 修改用户
     */
    boolean updateUser(Long userId, UserForm userForm);

    /**
     * 删除用户
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return boolean
     */
    boolean deleteUsers(String idsStr);

    /**
     * 根据用户ID获取用户登录后的信息
     */
    PetUserInfoVo getUserInfoById(Long uid);
}
