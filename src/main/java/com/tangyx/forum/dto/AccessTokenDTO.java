package com.tangyx.forum.dto;

/**
 * @author tangyx
 * @date 2021/9/21 19:01
 * +
 */
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
