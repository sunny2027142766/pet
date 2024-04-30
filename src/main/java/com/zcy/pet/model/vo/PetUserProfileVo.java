package com.zcy.pet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PetUserProfileVo {
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 地址
     */
    private String address;
    /**
     * 性别 0 女 1 男
     */
    private Integer sex;
    /**
     * 生日
     */
    private String birthday;

    /**
     * 发帖数
     */
    private Long postNum;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * 评论数
     */
    private Long commentNum;

    /**
     * 分享数
     */
    private Long shareNum;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
