package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.utils.DateUtils;
import com.zcy.pet.converter.PetInfoConverter;
import com.zcy.pet.converter.PetPostConverter;
import com.zcy.pet.mapper.PetInfoMapper;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetPostBo;
import com.zcy.pet.model.entity.PetInfo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.form.PetInfoForm;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.vo.PetInfoPageVo;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.service.PetInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetInfoServiceImpl extends ServiceImpl<PetInfoMapper, PetInfo> implements PetInfoService {
    private final PetInfoConverter petInfoConverter;

    @Override
    public List<PetInfo> getAllPetInfoList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetInfoPageVo> getPetInfoPageList(PetInfoPageQuery petInfoPageQuery) {
        // 构造参数
        Integer pageNum = petInfoPageQuery.getPageNum();
        Integer pageSize = petInfoPageQuery.getPageSize();
        Page<PetInfoBo> pageQuery = new Page<>(pageNum, pageSize);
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(petInfoPageQuery, "startTime", "endTime");
        // 查询数据
        Page<PetInfoBo> petInfoBo = this.baseMapper.getPagePetInfoList(pageQuery, petInfoPageQuery);
        // 数据转换
        return petInfoConverter.boToPageVo(petInfoBo);
    }

    @Override
    public boolean savePetInfo(PetInfoForm petInfoForm) {
        Long pid = petInfoForm.getPid();
        String name = petInfoForm.getName();
        long count = this.count(new LambdaQueryWrapper<PetInfo>()
                .ne(pid != null, PetInfo::getPid, pid)
                .and(
                        wrapper -> wrapper.eq(PetInfo::getName, name)
                ));
        Assert.isTrue(count == 0, "宠物名称已存在，请修改后重试！");
        // 实体转换
        PetInfo petInfo = petInfoConverter.form2Entity(petInfoForm);

        // 保存数据
        return this.save(petInfo);
    }

    @Override
    public boolean updatePetInfo(Long pid, PetInfoForm petInfoForm) {
        petInfoForm.setPid(pid);
        // 实体转换
        PetInfo petInfo = petInfoConverter.form2Entity(petInfoForm);
        // 保存数据
        return this.updateById(petInfo);
    }

    @Override
    public boolean deletePetInfo(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        //
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        // 删除字典数据项
        return this.removeByIds(ids);
    }
}
