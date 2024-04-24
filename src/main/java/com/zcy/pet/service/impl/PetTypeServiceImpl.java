package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetTypeConverter;
import com.zcy.pet.mapper.PetTypeMapper;
import com.zcy.pet.model.bo.PetTypeBo;
import com.zcy.pet.model.entity.PetType;
import com.zcy.pet.model.form.TypeForm;
import com.zcy.pet.model.query.PetTypePageQuery;
import com.zcy.pet.model.vo.PetTypePageVo;
import com.zcy.pet.service.PetTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PetTypeServiceImpl extends ServiceImpl<PetTypeMapper, PetType> implements PetTypeService {
    private final PetTypeConverter petTypeConverter;

    @Override
    public IPage<PetTypePageVo> getPetTypePageList(PetTypePageQuery petTypePageQuery) {
        // 构造参数
        Integer pageNum = petTypePageQuery.getPageNum();
        Integer pageSize = petTypePageQuery.getPageSize();
        Page<PetTypeBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petTypePageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetTypeBo> petTypeBo = this.baseMapper.getPagePetTypeList(pageQuery, petTypePageQuery);
        // 数据转换
        return petTypeConverter.boToPageVo(petTypeBo);
    }

    @Override
    public boolean saveType(TypeForm typeForm) {
        Long tid = typeForm.getTid();
        String name = typeForm.getName();
        long count = this.count(new LambdaQueryWrapper<PetType>()
                .ne(tid != null, PetType::getTid, tid)
                .and(
                        wrapper -> wrapper.eq(PetType::getName, name)
                ));
        Assert.isTrue(count == 0, "该宠物名称已存在，请修改后重试！");
        // 实体转换
        PetType type = petTypeConverter.form2Entity(typeForm);

        return this.save(type);
    }

    @Override
    public boolean updateType(Long tid, TypeForm typeForm) {
        Long tId = typeForm.getTid();
        String name = typeForm.getName();
        long count = this.count(new LambdaQueryWrapper<PetType>()
                .ne(tId != null, PetType::getTid, tId)
                .and(
                        wrapper -> wrapper.eq(PetType::getName, name)
                ));
        Assert.isTrue(count == 0, "该宠物名称已存在，请修改后重试！");
        typeForm.setTid(tid);
        // 实体转换
        PetType type = petTypeConverter.form2Entity(typeForm);
        return this.updateById(type);
    }

    @Override
    public boolean deleteTypes(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        //
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        // 删除字典数据项
        return this.removeByIds(ids);
    }

    @Override
    public List<Option> listTypeOptions() {
        // 查询数据
        List<PetType> typeList = this.list(new LambdaQueryWrapper<PetType>()
                .select(PetType::getTid, PetType::getName)
        );
        log.info("类型列表:{}", typeList);
        // 实体转换
        return petTypeConverter.entities2Options(typeList);
    }
}
