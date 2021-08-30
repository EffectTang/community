package com.tangyx.forum.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "hello";
    }
}
