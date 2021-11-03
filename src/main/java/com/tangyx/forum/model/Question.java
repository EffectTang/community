package com.tangyx.forum.model;

import lombok.Data;

/**
 * @author tangyx
 * @date 2021/10/8 23:35
 * +
 */

   /*
    *     title varchar(50),
    description text,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator int,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256)
    * */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Integer creator;


}
