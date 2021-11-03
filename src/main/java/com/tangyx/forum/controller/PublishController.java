package com.tangyx.forum.controller;

import com.tangyx.forum.mapper.QuestionMapper;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.Question;
import com.tangyx.forum.model.User;
import com.tangyx.forum.utils.GetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangyx
 * @date 2021/10/4 22:34
 * +
 */
@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GetToken getToken;

    @RequestMapping("/publish")
    public String publish(){

        return "publish";
    }
//   http://localhost:8080/publish?title=  8&description=
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
            HttpServletRequest request,
            Model model){
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = getToken.getUser(request);
        if(user!=null){
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);

            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.createQuestion(question);
            return "redirect:/";
        }
        else{
            model.addAttribute("error","用户未登录");
            System.out.println("对不起 你没有登录");
        }



//        questionMapper.createQuestion(question);

        return "publish";
    }

}
