package com.zcy.pet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcy.pet.common.model.Option;
import com.zcy.pet.model.entity.PetMenu;
import com.zcy.pet.model.form.MenuForm;
import com.zcy.pet.model.query.PetInfoPageQuery;
import com.zcy.pet.model.query.PetMenuPageQuery;
import com.zcy.pet.model.vo.PetInfoVo;
import com.zcy.pet.model.vo.PetMenuPageVo;
import com.zcy.pet.model.vo.PetMenuVo;

import java.util.List;

public interface PetMenuService extends IService<PetMenu> {
    List<PetMenu> getAllPetMenuList();

    IPage<PetMenuPageVo> getPetMenuPageList(PetMenuPageQuery petMenuPageQuery);

    boolean saveMenu(MenuForm menuForm);

    boolean updateMenu(Long mid, MenuForm menuForm);

    boolean deleteMenus(String idStr);

    List<Option> listMenuOptions();
}
