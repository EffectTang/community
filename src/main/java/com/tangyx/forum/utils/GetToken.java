package com.tangyx.forum.utils;

import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tangyx
 * @date 2021/10/28 23:05
 * +
 */

@Component
public class GetToken {

    @Autowired
    UserMapper userMapper;


    public User getUser(HttpServletRequest request){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null)
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                     user = userMapper.findByToken(token);
                    if (user!=null)
                        request.getSession().setAttribute("user", user);
                    break;
                }

            }
        return user;
    }

}
