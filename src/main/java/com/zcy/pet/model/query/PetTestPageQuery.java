package com.zcy.pet.model.query;

import com.zcy.pet.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description ="测试分页查询对象")
public class PetTestPageQuery extends BasePageQuery {
    @Schema(description ="ID")
    private Long id;

    @Schema(description ="姓名")
    private String name;

    @Schema(description ="年龄")
    private Integer age;

    @Schema(description ="创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @Schema(description ="更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
}
