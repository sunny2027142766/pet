package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.query.PetRolePageQuery;
import com.zcy.pet.model.vo.PetRolePageVo;
import com.zcy.pet.model.vo.PetRoleVo;

import java.util.List;

public interface PetRoleService extends IService<PetRole> {
    /**
     * 获取所有用户信息
     */
    List<PetRoleVo> getAllPetRole();

    /**
     * 分页查询
     */
    IPage<PetRolePageVo> getPetRolePageList(PetRolePageQuery petRolePageQuery);

    /**
     * 获取角色下拉列表
     * @return List<Option>
     */
    List<Option> listRoleOptions();

    /**
     * 添加角色
     * @param roleForm 角色表单对象
     * @return boolean
     */
    boolean saveRole(RoleForm roleForm);

    /**
     * 修改角色
     * @param rid 角色ID
     * @param roleForm 角色表单对象
     * @return boolean
     */
    boolean updateRole(Long rid,RoleForm roleForm);

    /**
     * 批量删除角色
     * @param ids 角色ID，多个使用英文逗号(,)分割
     * @return boolean
     */
    boolean deleteRoles(String ids);

}
