package com.tangyx.forum.dto;

import lombok.Data;

/**
 * @author tangyx
 * @date 2021/9/22 0:06
 * +
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;
    private String avatar_url;

}
