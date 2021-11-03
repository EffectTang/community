package com.tangyx.forum.model;

import lombok.Data;

/**
 * @author tangyx
 * @date 2021/9/28 23:45
 * +
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String token;
    private String accountId;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
