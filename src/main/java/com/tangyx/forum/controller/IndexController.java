package com.tangyx.forum.controller;

import com.tangyx.forum.dto.QuestionDTO;
import com.tangyx.forum.mapper.QuestionMapper;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.Question;
import com.tangyx.forum.model.User;
import com.tangyx.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tangyx
 * @date 2021/10/2 16:32
 * +
 */

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
        Model model){

        Cookie[] cookies = request.getCookies();
        if(cookies!=null)
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user!=null)
                    request.getSession().setAttribute("user", user);
                break;
            }
        }
        List<QuestionDTO> questionlist = questionService.list();
        model.addAttribute("questionlist",questionlist);
        return "index";
    }
}
