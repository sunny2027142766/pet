package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.mapper.PetIconMapper;
import com.zcy.pet.model.entity.PetIcon;
import com.zcy.pet.model.form.IconForm;
import com.zcy.pet.service.PetIconService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetIconServiceImpl extends ServiceImpl<PetIconMapper, PetIcon> implements PetIconService {

    @Override
    public List<PetIcon> getAllIconList(String name) {
        // 根据name迷糊查询
        LambdaQueryWrapper<PetIcon> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(name), PetIcon::getName, name);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean saveIcon(IconForm iconForm) {
        int i = this.baseMapper.insert(new PetIcon(null, iconForm.getName(), iconForm.getPath()));
        return i > 0;
    }

    @Override
    public boolean updateIcon(Long id, IconForm iconForm) {
        String name = iconForm.getName();
        // 查询用户是否存在
        long count = this.count(new LambdaQueryWrapper<PetIcon>().eq(PetIcon::getName, name));
        Assert.isTrue(count == 0, "该名称已存在,请更换其他名称重试");
        int i = this.baseMapper.updateById(new PetIcon(id, name, iconForm.getPath()));
        return i > 0;
    }

    @Override
    public boolean deleteIcons(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        // 获取要删除的ids数组
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        // 验证菜单表中该ID是否被关联
        long count = this.count(new LambdaQueryWrapper<PetIcon>().in(PetIcon::getId, ids));
        Assert.isTrue(count == 0, "该图标已关联菜单，无法删除");
        // 删除字典数据项
        return this.removeByIds(ids);
    }
}
