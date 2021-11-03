package com.tangyx.forum.dto;

import lombok.Data;

/**
 * @author tangyx
 * @date 2021/9/21 19:01
 * +
 */
@Data
public class AccessTokenDTO {
    /*
    client_id	string	必需的。您从 GitHub 收到的 OAuth 应用程序的客户端 ID。
client_secret	string	必需的。您从 GitHub 收到的 OAuth 应用程序的客户端密钥。
code	string	必需的。您收到的作为对步骤 1 的响应的代码。
redirect_uri	string	您的应用程序中授权后将用户发送到的 URL。
     */
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;

    private String state;


}
