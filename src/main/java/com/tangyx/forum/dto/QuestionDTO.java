package com.tangyx.forum.dto;

import com.tangyx.forum.model.User;
import lombok.Data;

/**
 * @author tangyx
 * @date 2021/11/2 22:14
 * +
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Long creator;

    private User user;
}
