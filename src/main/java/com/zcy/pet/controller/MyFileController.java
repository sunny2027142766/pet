package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Tag(name = "文件管理", description = "文件管理接口")
@Slf4j
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class MyFileController {
    @Value("${file.path}")
    private String path;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile multipartFile/*, @RequestParam("files") MultipartFile[] multipartFiles*/) {
        long size = multipartFile.getSize();
        String originalFilename = multipartFile.getOriginalFilename();
        String pathTemp = new Date().getTime() + originalFilename;
        String realpath = path + pathTemp;
        String resultpath = "/Myfile/" + pathTemp;
        try {
            multipartFile.transferTo(new File(realpath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success(resultpath);
    }


    @PostMapping("/uploads")
    public Result<List<String>> upload(@RequestParam("files") MultipartFile[] multipartFile) {
        ArrayList<String> strings = new ArrayList<>();
        long size = multipartFile.length;
        try {
            for (MultipartFile file : multipartFile) {
                String originalFilename = file.getOriginalFilename();
                String pathTemp = new Date().getTime() + originalFilename;
                String loaclpath = path + pathTemp;
                file.transferTo(new File(loaclpath));
                strings.add("/Myfile/" + pathTemp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success(strings);
    }
}