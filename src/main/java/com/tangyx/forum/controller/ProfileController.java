package com.tangyx.forum.controller;

import com.tangyx.forum.dto.PageDTO;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.User;
import com.tangyx.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangyx
 * @date 2021/11/10 23:09
 * +
 */
@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            HttpServletRequest request,
            @RequestParam(name = "page" ,defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "5")Integer size,
            Model model)
    {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回答");
        }

        PageDTO pageDTO = questionService.listByUser(user.getId(),page,size);
        model.addAttribute("userpages",pageDTO);

        return "profile";
    }
}
