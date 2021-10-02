package com.tangyx.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tangyx
 * @date 2021/8/10 23:22
 * +
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String greeting(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name",name);

        return "index";
    }

//    @RequestMapping("/")  // 反斜杠表示 什么都不输入的情况下 就适配这个方法
//    public String showIndex(){
//        return "index";
//    }

}
