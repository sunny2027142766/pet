package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 评论分页vo视图
 */
@Data
public class PetCommentPageVo {
    private Long cid;
    private String username;
    private String content;
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
