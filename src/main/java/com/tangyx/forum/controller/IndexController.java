package com.tangyx.forum.controller;

import com.tangyx.forum.dto.PageDTO;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String index(Model model,
                        @RequestParam(name = "page" ,defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size)
    {
        //当前页数 page 和每页展示的数量 size
        PageDTO pages = questionService.list(page,size);
        model.addAttribute("pagesdto",pages);
        return "index";
    }

    @GetMapping("/loginout")
    public String loginOut(HttpServletRequest request,
                           HttpServletResponse response){
//      删除 Session
        request.getSession().removeAttribute("user");
        //删除 cookie  cookie名字要相同 且设置 maxage 为0
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0); //
        response.addCookie(cookie);
        return "redirect:/";
    }
}
