package com.tangyx.forum.utils;

import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.User;
import com.tangyx.forum.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                     List<User> users= userMapper.selectByExample(userExample);
                    if (users.size()!=0)
                        request.getSession().setAttribute("user", users.get(0));
                    break;
                }

            }
        return user;
    }

}
