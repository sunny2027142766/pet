package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.result.PageResult;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.mapper.PetUserMapper;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.vo.PetUserVo;
import com.zcy.pet.service.PetUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetUserServiceImpl extends ServiceImpl<PetUserMapper, PetUser> implements PetUserService {

    private final PetUserConverter petUserConverter;

    @Override
    public List<PetUserVo> getAllPetUser() {
        List<PetUser> petUsers = this.baseMapper.getAllPetUser();
        return petUserConverter.entityToVo(petUsers);
    }

    @Override
    public PageResult<PetUserVo> getAllPetUserPage(Integer pageNo, Integer pageSize) {
        // TODO: 2024/4/15 分页查询参数构造区
        LambdaQueryWrapper<PetUser> queryWrapper = new LambdaQueryWrapper<>();
        IPage<PetUser> page = new Page<PetUser>(pageNo, pageSize);
        this.baseMapper.selectPage(page, queryWrapper);
        List<PetUserVo> petUserVos = petUserConverter.entityToVo(page.getRecords());

        PageResult<PetUserVo> pageResult = new PageResult<>();
        PageResult.Data<PetUserVo> data = new PageResult.Data<>();
        data.setList(petUserVos);
        data.setTotal(page.getTotal());
        pageResult.setData(data);
        return pageResult;
    }

    @Override
    public PetUser getUserByEmail(String email) {
        QueryWrapper<PetUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        PetUser petUser = this.getOne(queryWrapper);
        // 根据邮箱获取用户是否存在
        return petUser;
    }


    @Override
    public PetUserVo getUserById(Integer id) {
        PetUser petUser = this.baseMapper.selectById(id);
        PetUserVo petUserVo = petUserConverter.entityToVo(petUser);
        return petUserVo;
    }


    @Override
    public boolean updateUserById(PetUser petUser) {
        int i = this.baseMapper.updateById(petUser);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
