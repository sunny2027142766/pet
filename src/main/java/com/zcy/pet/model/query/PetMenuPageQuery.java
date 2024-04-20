package com.zcy.pet.model.query;

import com.zcy.pet.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description ="菜单分页查询对象")
public class PetMenuPageQuery extends BasePageQuery {

    @Schema(description ="菜单标题")
    private String title;

    @Schema(description ="创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @Schema(description ="更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;


}
