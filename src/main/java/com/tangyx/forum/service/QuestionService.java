package com.tangyx.forum.service;

import com.tangyx.forum.dto.QuestionDTO;
import com.tangyx.forum.mapper.QuestionMapper;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.Question;
import com.tangyx.forum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tangyx
 * @date 2021/11/2 22:22
 * +
 */
@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;


    public List<QuestionDTO> list() {
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = questionMapper.list();
        if(questions!=null)
            for (Question question:questions) {
                User user = userMapper.findByCreator(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        return  questionDTOS;
    }
}
