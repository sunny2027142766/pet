package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetModelConverter;
import com.zcy.pet.mapper.PetModelMapper;
import com.zcy.pet.model.bo.PetModelBo;
import com.zcy.pet.model.entity.PetModel;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.PetModelForm;
import com.zcy.pet.model.query.PetModelPageQuery;
import com.zcy.pet.model.vo.PetModeConfigVo;
import com.zcy.pet.model.vo.PetModelPageVo;
import com.zcy.pet.service.PetModelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PetModelServiceImpl extends ServiceImpl<PetModelMapper, PetModel> implements PetModelService {
    private final PetModelConverter petModelConverter;

    @Override
    public List<PetModel> getAllPetModelList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetModelPageVo> getPetModelPageList(PetModelPageQuery petModelPageQuery) {
        // 构造参数
        Integer pageNum = petModelPageQuery.getPageNum();
        Integer pageSize = petModelPageQuery.getPageSize();
        Page<PetModelBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petModelPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetModelBo> petModelBo = this.baseMapper.getPagePetModelList(pageQuery, petModelPageQuery);
        // 数据转换
        return petModelConverter.boToPageVo(petModelBo);
    }

    @Override
    public boolean savePetModel(PetModelForm petModelForm) {
        Long mid = petModelForm.getMid();
        String name = petModelForm.getName();
        long count = this.count(new LambdaQueryWrapper<PetModel>()
                .ne(mid != null, PetModel::getMid, mid)
                .and(
                        wrapper -> wrapper.eq(PetModel::getName, name)
                ));
        Assert.isTrue(count == 0, "模型名称已存在，请修改后重试！");
        // 实体转换
        PetModel petModel = petModelConverter.form2Entity(petModelForm);

        // 保存数据
        return this.save(petModel);
    }

    @Override
    public boolean updatePetModel(Long mid, PetModelForm petModelForm) {
        String name = petModelForm.getName();
        long count = this.count(new LambdaQueryWrapper<PetModel>()
                .ne(mid != null, PetModel::getMid, mid)
                .and(
                        wrapper -> wrapper.eq(PetModel::getName, name)
                ));
        Assert.isTrue(count == 0, "模型名称已存在，请修改后重试！");
        petModelForm.setMid(mid);
        // 实体转换
        PetModel petModel = petModelConverter.form2Entity(petModelForm);
        return this.updateById(petModel);
    }

    @Override
    public List<Option> listModelOptions() {
        // 查询数据
        List<PetModel> modelList = this.list(new LambdaQueryWrapper<PetModel>()
                .select(PetModel::getMid, PetModel::getName)
        );
        log.info("模型列表:{}", modelList);
        // 实体转换
        return petModelConverter.entities2Options(modelList);

    }

    @Override
    public List<PetModeConfigVo> listModelConfig() {
        return this.baseMapper.listModelConfig();
    }
}
