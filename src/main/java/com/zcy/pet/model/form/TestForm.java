package com.zcy.pet.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "测试表单对象")
@Data
public class TestForm {
    @Schema(description = "姓名")
    private String name;

    @Schema(description = "年龄")
    private Integer age;
}
