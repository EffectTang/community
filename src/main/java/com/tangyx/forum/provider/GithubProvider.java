package com.tangyx.forum.provider;

import com.alibaba.fastjson.JSON;
import com.tangyx.forum.dto.AccessTokenDTO;
import com.tangyx.forum.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author tangyx
 * @date 2021/9/21 18:22
 * +
 */
@Component
public class GithubProvider {

    public String getAcessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
//                 https://api.github.com/user/repos?access_token=my_access_token
                .url("https://api.github.com/user")
                .header("Authorization","Bearer "+accessToken)
                .get()
                .build();
        try (
            Response response = client.newCall(request).execute()) {
            String result =response.body().string();
            GithubUser githubUser = JSON.parseObject(result,GithubUser.class);

            return githubUser;
        }catch (IOException e){
            e.printStackTrace();
        }
        return  null;
    }
}
