package com.tangyx.forum.controller;

import com.tangyx.forum.mapper.QuestionMapper;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.Question;
import com.tangyx.forum.model.User;
import com.tangyx.forum.service.QuestionService;
import com.tangyx.forum.utils.GetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    QuestionService questionService;

    @RequestMapping("/publish")
    public String publish(){

        return "publish";
    }
//   http://localhost:8080/publish?title=  8&description=
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value ="title",required = false) String title,
            @RequestParam(value ="description",required = false)String description,
            @RequestParam(value ="tag", required = false)String tag,
            @RequestParam(value = "id",required = false)Long id,
            HttpServletRequest request,
            Model model){
        if(title==null|| title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null|| description.equals("")){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag==null|| tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            questionService.updateQuestion(question,id);
            return "redirect:/";
        }
        else{
            model.addAttribute("error","用户未登录");
            System.out.println("对不起 你没有登录");
        }
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String editPublish(
            @PathVariable("id")Long id,
            Model model
    ){
        Question question =questionMapper.selectByPrimaryKey(id);
//                questionMapper.findByID(id); 被MBG替代
        model.addAttribute("id",id);

        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        return "publish";
    }
}
