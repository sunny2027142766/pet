package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetPermission;
import com.zcy.pet.model.form.PermForm;
import com.zcy.pet.model.form.RoleForm;
import com.zcy.pet.model.query.PetPermPageQuery;
import com.zcy.pet.model.vo.PetPermissionVo;
import com.zcy.pet.model.vo.PetRolePageVo;

import java.util.List;

public interface PetPermissionService extends IService<PetPermission> {
    List<PetPermissionVo> getAllPetPermissionList();

    /**
     * 权限列表分页查询
     * @param petPermPageQuery 分页查询参数
     * @return IPage<PetPermissionVo>
     */
    IPage<PetPermissionVo> getPetPermPageList(PetPermPageQuery petPermPageQuery);

    /**
     * 增加权限
     * @param permForm 权限表单对象
     * @return boolean
     */
    boolean savePerm(PermForm permForm);

    /**
     * 修改权限
     * @param pid 权限ID
     * @param permForm 权限表单对象
     * @return boolean
     */
    boolean updatePerm(Long pid, PermForm permForm);

    /**
     * 删除权限
     * @param ids 权限ID集合
     * @return boolean
     */
    boolean deletePerms(String ids);


}
