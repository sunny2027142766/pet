package com.zcy.pet.controller;

import com.zcy.pet.common.result.Result;
import com.zcy.pet.utils.FileUtils;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Tag(name = "文件管理", description = "文件管理接口")
@Slf4j
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class MyFileController {
    @Value("${file.path}")
    private String path;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile multipartFile) {
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

    @PostMapping("/zip")
    public Result<String> uploadZip(@RequestParam("file") MultipartFile multipartFile) {
        // 检查文件是否为空
        if (multipartFile.isEmpty()) {
            return Result.failed("上传的文件为空");
        }
        String originalFilename = FileUtils.getFileNameWithoutExtension(multipartFile);

        // 指定服务器上保存文件的路径
        String uploadDir = path + "/models/" + originalFilename;

        // 创建临时文件夹用于解压缩
        File tempDir = new File(uploadDir);
        tempDir.mkdirs();

        try {
            // 解压缩文件
            ZipInputStream zipInputStream = new ZipInputStream(multipartFile.getInputStream());
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                String entryName = zipEntry.getName();
                File entryFile = new File(tempDir, entryName);
                if (zipEntry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    File parentDir = entryFile.getParentFile();
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                }
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();

            // 将解压后的文件移动到目标位置
            File[] files = tempDir.listFiles();
            for (File file : files) {
                file.renameTo(new File(uploadDir, file.getName()));
            }
            String resultpath = "/Myfile/models/" + originalFilename;
            return Result.success(resultpath);
        } catch (IOException e) {
            log.info("文件上传失败:{}", e.getMessage());
            return Result.failed("文件上传失败: ");
        }
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
