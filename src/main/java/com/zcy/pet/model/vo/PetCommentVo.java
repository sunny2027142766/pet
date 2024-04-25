package com.zcy.pet.model.vo;

import lombok.Data;

@Data
public class PetCommentVo {
    private Long cid; // 评论ID
    private String content; // 评论内容
    private String createTime; // 评论时间
    private String avatar; // 评论用户头像
    private String username; // 评论用户名
}
