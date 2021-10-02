package com.tangyx.forum.controller;

import com.tangyx.forum.dto.AccessTokenDTO;
import com.tangyx.forum.dto.GithubUser;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.User;
import com.tangyx.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
//                           @RequestParam(name = "status") String status
                            HttpServletResponse response
    ){
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setClient_id("ff89e30991e3901115fa");
        accessTokenDTO.setClient_secret("6d07f6f24fdb3f41a97cab58fbccbaebed65e6ef");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
//        accessTokenDTO.setState(status);

        String acesstoken = githubProvider.getAcessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(acesstoken);
        if(githubUser!=null) {
            //将获取到的用户写到数据库中
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);

            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }
}
