package com.zcy.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.converter.PetUserConverter;
import com.zcy.pet.mapper.PetUserMapper;
import com.zcy.pet.model.bo.PetUserBo;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.query.PetUserPageQuery;
import com.zcy.pet.model.vo.PetUserPageVo;
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
    public IPage<PetUserPageVo> getPetUserPageList(PetUserPageQuery petUserPageQuery) {
        // 构造参数
        Integer pageNum = petUserPageQuery.getPageNum();
        Integer pageSize = petUserPageQuery.getPageSize();
        Page<PetUserBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetUserBo> petUserBo = this.baseMapper.getPagePetUserList(pageQuery, petUserPageQuery);
        // 数据转换
        return petUserConverter.boToPageVo(petUserBo);
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
