package com.tangyx.forum.service;

import com.tangyx.forum.dto.PageDTO;
import com.tangyx.forum.dto.QuestionDTO;
import com.tangyx.forum.exception.CustomException;
import com.tangyx.forum.exception.CustomizeErrorCode;
import com.tangyx.forum.mapper.QuestionExtMapper;
import com.tangyx.forum.mapper.QuestionMapper;
import com.tangyx.forum.mapper.UserMapper;
import com.tangyx.forum.model.Question;
import com.tangyx.forum.model.QuestionExample;
import com.tangyx.forum.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    QuestionExtMapper questionExtMapper;


    public PageDTO list(Integer page, Integer size) {

        Integer offset = size*(page-1);

        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
//                listPage(offset,size);
        PageDTO pageDTOS = new PageDTO();

        if(questions!=null)
            for (Question question:questions) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        pageDTOS.setQuestionsdto(questionDTOS);
        Integer countpages = (int)questionMapper.countByExample(new QuestionExample());
//                list();
        pageDTOS.setPageTion(countpages,page,size);
        return  pageDTOS;
    }

    public PageDTO listByUser(Long userid, Integer page, Integer size) {
        Integer offset = size*(page-1);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userid);
        List<Question> questions =
                questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        PageDTO pageDTOS = new PageDTO();

        if(questions!=null){
            for (Question question:questions) {
                User user = userMapper.selectByPrimaryKey(userid);
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOS.add(questionDTO);
            }
        }
        pageDTOS.setQuestionsdto(questionDTOS);

        Integer counttotal = (int)questionMapper.countByExample(new QuestionExample());
        pageDTOS.setPageTion(counttotal,page,size);
        return pageDTOS;
    }

    public QuestionDTO findByID(Long questionId) {
        QuestionDTO questionDTO = new QuestionDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(questionId);
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if(question==null){
            throw new CustomException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        BeanUtils.copyProperties(question,questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }


    public void updateQuestion(Question question, Long id) {
        if(id!=null){
            Question updateQuestion = new Question();
//           注意这里的question 只有4个字段有值
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();

            example.createCriteria().andIdEqualTo(id);
//                    .andIdEqualTo(question.getId());

            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            System.out.println(updated);
            if(updated!=1){
                throw new CustomException(CustomizeErrorCode.QUESTION_UPDADE_NOT_FOUND);
            }
        }else {
            question.setId(id);
            question.setLikeCount(0);
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setGmtModified(question.getGmtCreate());
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.insert(question);
        }
    }

    public void incView(Long id){
        Question question = questionMapper.selectByPrimaryKey(id);
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

}
