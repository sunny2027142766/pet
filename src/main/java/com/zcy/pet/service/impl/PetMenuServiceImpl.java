package com.zcy.pet.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.converter.PetInfoConverter;
import com.zcy.pet.converter.PetMenuConverter;
import com.zcy.pet.mapper.PetMenuMapper;
import com.zcy.pet.model.bo.PetInfoBo;
import com.zcy.pet.model.bo.PetMenuBo;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.entity.PetRole;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.vo.PetMenuPageVo;
import com.zcy.pet.model.vo.PetMenuVo;
import com.zcy.pet.service.PetMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PetMenuServiceImpl extends ServiceImpl<PetMenuMapper, PetMenu> implements PetMenuService {
    private final PetMenuConverter petMenuConverter;

    @Override
    public List<PetMenu> getAllPetMenuList() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public IPage<PetMenuPageVo> getPetMenuPageList(PetMenuPageQuery petMenuPageQuery) {
        // 构造参数
        Integer pageNum = petMenuPageQuery.getPageNum();
        Integer pageSize = petMenuPageQuery.getPageSize();
        Page<PetMenuBo> pageQuery = new Page<>(pageNum, pageSize);
        // 查询数据
        Page<PetMenuBo> petMenuBo = this.baseMapper.getPagePetMenuList(pageQuery, petMenuPageQuery);
        // 数据转换
        return petMenuConverter.boToPageVo(petMenuBo);
    }

    @Override
    public boolean saveMenu(MenuForm menuForm) {
        Long mId = menuForm.getMid();
        String path = menuForm.getPath();
        long count = this.count(new LambdaQueryWrapper<PetMenu>()
                .ne(mId != null, PetMenu::getMid, mId)
                .and(
                        wrapper -> wrapper.eq(PetMenu::getPath, path)
                ));
        Assert.isTrue(count == 0, "菜单路径已存在，请修改后重试！");
        // 实体转换
        PetMenu menu = petMenuConverter.form2Entity(menuForm);

        return this.save(menu);
    }

    @Override
    public boolean updateMenu(Long mid, MenuForm menuForm) {
        Long mId = menuForm.getMid();
        String path = menuForm.getPath();
        long count = this.count(new LambdaQueryWrapper<PetMenu>()
                .ne(mId != null, PetMenu::getMid, mId)
                .and(
                        wrapper -> wrapper.eq(PetMenu::getPath, path)
                ));
        Assert.isTrue(count == 0, "菜单路径已存在，请修改后重试！");
        menuForm.setMid(mid);
        // 实体转换
        PetMenu menu = petMenuConverter.form2Entity(menuForm);
        return this.updateById(menu);
    }

    @Override
    public boolean deleteMenus(String idStr) {
        Assert.isTrue(StrUtil.isNotBlank(idStr), "删除的数据为空");

        // 逻辑删除
        List<Long> ids = Arrays.stream(idStr.split(","))
                .map(Long::parseLong)
                .toList();

        return this.removeByIds(ids);
    }

    @Override
    public List<Option> listMenuOptions() {
        // 查询数据
        List<PetMenu> petMenus = this.list(new LambdaQueryWrapper<PetMenu>()
                .select(PetMenu::getMid, PetMenu::getTitle)
        );
        log.info("菜单列表:{}", petMenus);
        // 实体转换
        return petMenuConverter.entities2Options(petMenus);
    }
}
