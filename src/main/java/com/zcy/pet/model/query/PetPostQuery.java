package com.zcy.pet.model.query;

import com.zcy.pet.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description ="帖子查询对象")
public class PetPostQuery extends BasePageQuery {

    @Schema(description ="帖子名")
    private String title;

    @Schema(description ="状态")
    private Integer status;

    @Schema(description ="创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Schema(description ="创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;
}
