package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.model.entity.PetTest;
import com.zcy.pet.model.form.TestForm;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetTestVo;

import java.util.List;


public interface PetTestService extends IService<PetTest> {
    /**
     * 获取所有数据
     */
    List<PetTestVo> getAllPetTestList();

    /**
     * 分页查询
     */
    IPage<PetTestPageVo> getPetTestPageList(PetTestPageQuery petTestPageQuery);

    /**
     * 新增测试数据
     * @param test 数据表单
     * @return boolean
     */
    boolean saveTest(TestForm test);

    /**
     * 修改测试数据
     * @param id id
     * @param testForm 数据表单
     * @return boolean
     */
    boolean updateTest(Long id,TestForm testForm);

    /**
     * 删除测试数据
     * @param ids id组合，多个以英文(,)分割
     * @return boolean
     */
    boolean deleteTest(String ids);

}
