package com.zcy.pet.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "权限模块", description = "权限模块接口")
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class PetPermissionController {
}
