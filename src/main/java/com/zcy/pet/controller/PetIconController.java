package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.model.entity.PetIcon;
import com.zcy.pet.model.form.IconForm;
import com.zcy.pet.service.PetIconService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "图标模块", description = "图标模块接口")
@Slf4j
@RestController
@RequestMapping("/api/icon")
@RequiredArgsConstructor
public class PetIconController {

    private final PetIconService petIconService;
    @Operation(description = "查询所有图标")
    @GetMapping
    public Result<List<PetIcon>> getAllIcons(@RequestParam("name") String name){
        //  调用service查询所有结果
        List<PetIcon> iconList = petIconService.getAllIconList(name);
        // 封装结果
        return Result.success(iconList);
    }

    @Operation(description = "添加图标")
    @PostMapping
    public Result<String> addIcon(@RequestBody IconForm iconForm){
        boolean saveIcon = petIconService.saveIcon(iconForm);
        return Result.judge(saveIcon);
    }

    @Operation(description = "更新图标")
    @PutMapping("/{id}")
    public Result<String> updateTest(@PathVariable("id") Long id, @RequestBody IconForm iconForm){
        boolean updateIcon = petIconService.updateIcon(id,iconForm);
        return Result.judge(updateIcon);
    }

    @Operation(description = "删除图标")
    @DeleteMapping("/{ids}")
    public  Result<String> deleteIcons(@PathVariable("ids") String ids){
        boolean result = petIconService.deleteIcons(ids);
        return Result.judge(result);
    }
}
