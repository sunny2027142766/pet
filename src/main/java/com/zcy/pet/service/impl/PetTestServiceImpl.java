package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetTestConverter;
import com.zcy.pet.mapper.PetTestMapper;
import com.zcy.pet.model.bo.PetTestBo;
import com.zcy.pet.model.entity.PetTest;
import com.zcy.pet.model.form.TestForm;
import com.zcy.pet.model.query.PetTestPageQuery;
import com.zcy.pet.model.vo.PetTestPageVo;
import com.zcy.pet.model.vo.PetTestVo;
import com.zcy.pet.service.PetTestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetTestServiceImpl extends ServiceImpl<PetTestMapper, PetTest> implements PetTestService {
    private final PetTestConverter petTestConverter;

    @Override
    public List<PetTestVo> getAllPetTestList() {
        List<PetTest> petTestList = this.baseMapper.getAllPetTestList();
        return petTestConverter.entityToVo(petTestList);
    }

    @Override
    public IPage<PetTestPageVo> getPetTestPageList(PetTestPageQuery petTestPageQuery) {
        // 构造参数
        Integer pageNum = petTestPageQuery.getPageNum();
        Integer pageSize = petTestPageQuery.getPageSize();
        Page<PetTestBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetTestBo> petTestBo = this.baseMapper.getPagePetTestList(pageQuery, petTestPageQuery);
        // 数据转换
        return petTestConverter.boToPageVo(petTestBo);
    }

    @Override
    public boolean saveTest(TestForm test) {
        String name=test.getName();
        // 查询用户是否存在
        long count = this.count(new LambdaQueryWrapper<PetTest>().eq(PetTest::getName, name));
        Assert.isTrue(count == 0, "名称已存在");

        // 实体转换 form->entity
        PetTest entity = petTestConverter.form2Entity(test);
        //新增测试数据
        return this.save(entity);
    }

    @Override
    public boolean updateTest(Long id, TestForm testForm) {
        // 查询用户是否存在
        long count = this.count(new LambdaQueryWrapper<PetTest>()
                .eq(PetTest::getName, testForm.getName())
                .ne(PetTest::getId, id)
        );
        Assert.isTrue(count == 0, "名称已存在");

        // form -> entity
        PetTest entity = petTestConverter.form2Entity(testForm);
        entity.setId(id);
        // 修改用户
        return this.updateById(entity);
    }

    @Override
    public boolean deleteTest(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的数据为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }
}
