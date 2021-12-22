package com.tangyx.forum.controller;

import com.tangyx.forum.dto.QuestionDTO;
import com.tangyx.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tangyx
 * @date 2021/11/21 22:37
 * +
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String showInformation(@PathVariable("id")Long id,
                                  Model model){
        questionService.incView(id);
        QuestionDTO question = questionService.findByID(id);
        model.addAttribute("question",question);
        return "question";

    }
}
