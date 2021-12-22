package com.tangyx.forum.controller;

import com.tangyx.forum.dto.AccessTokenDTO;
import com.tangyx.forum.dto.GithubUser;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.User;
import com.tangyx.forum.model.UserExample;
import com.tangyx.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
        UserExample userExample = new UserExample();
        userExample.createCriteria().
                andAccountIdEqualTo(String.valueOf(githubUser.getId()));
        List<User> is_existence = userMapper.selectByExample(userExample);
//                userMapper.findByAccount(String.valueOf(githubUser.getId()));


//        githubUser!=null&&
        if(is_existence.size()==0) {
            User user = new User();
            //将获取到的用户写到数据库中
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);

            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            String token = UUID.randomUUID().toString();
            User user = is_existence.get(0);

            User mgbuser =new User();
            mgbuser.setToken(token);
            mgbuser.setGmtModified(System.currentTimeMillis());
            mgbuser.setName(githubUser.getName());
            mgbuser.setAvatarUrl(githubUser.getAvatar_url());

            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andIdEqualTo(user.getId());

            userMapper.updateByExampleSelective(mgbuser,userExample1);

//            userMapper.updateUser(is_existence); 被mbg替代 猜测不传参 表示所有
            response.addCookie(new Cookie("token",mgbuser.getToken()));
            return "redirect:/";
        }

    }
}
