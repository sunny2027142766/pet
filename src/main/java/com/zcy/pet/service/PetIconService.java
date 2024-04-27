package com.zcy.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetIcon;
import com.zcy.pet.model.form.IconForm;

import java.util.List;


public interface PetIconService extends IService<PetIcon> {
    /**
     * 获取所有数据
     */
    List<PetIcon> getAllIconList(String name);

    /**
     * 新增测试数据
     * @param iconForm 数据表单
     * @return boolean
     */
    boolean saveIcon(IconForm iconForm);

    /**
     * 修改测试数据
     * @param id id
     * @param iconForm 数据表单
     * @return boolean
     */
    boolean updateIcon(Long id,IconForm iconForm);

    /**
     * 删除测试数据
     * @param ids id组合，多个以英文(,)分割
     * @return boolean
     */
    boolean deleteIcons(String ids);

}
