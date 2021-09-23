package com.tangyx.forum.controller;

import com.tangyx.forum.dto.AccessTokenDTO;
import com.tangyx.forum.dto.GithubUser;
import com.tangyx.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tangyx
 * @date 2021/9/13 23:42
 * +
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSECRET;

    @Value("${github.redirect.uri}")
    private String redirectURI;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code
//                           @RequestParam(name = "status") String status
    ){
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setClient_id("ff89e30991e3901115fa");
        accessTokenDTO.setClient_secret("6d07f6f24fdb3f41a97cab58fbccbaebed65e6ef");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
//        accessTokenDTO.setState(status);

        String token = githubProvider.getAcessToken(accessTokenDTO);
        GithubUser user = githubProvider.getGithubUser(token);
        System.out.println(user.getName());
        return "index";
    }
}
