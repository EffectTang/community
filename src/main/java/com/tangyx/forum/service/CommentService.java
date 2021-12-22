package com.tangyx.forum.service;

import com.tangyx.forum.enums.CommentTypeEnums;
import com.tangyx.forum.exception.CustomException;
import com.tangyx.forum.exception.CustomizeErrorCode;
import com.tangyx.forum.mapper.ContentMapper;
import com.tangyx.forum.mapper.QuestionExtMapper;
import com.tangyx.forum.mapper.QuestionMapper;
import com.tangyx.forum.model.Content;
import com.tangyx.forum.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tangyx
 * @date 2021/12/15 22:57
 * +
 */
@Service
public class CommentService {

    @Autowired
    ContentMapper contentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionExtMapper questionExtMapper;

    @Transactional
    public void insert(Content content) {
        if (content.getParentId()==null||content.getParentId()==0){
            throw new CustomException(CustomizeErrorCode.COMMENT_NOT_PARENT_ID);
        }
        if(content.getType()==null||!CommentTypeEnums.isExist(content.getType())){
            throw new CustomException(CustomizeErrorCode.COMMENT_PARENT_NOT);
        }
        if(content.getType().equals(CommentTypeEnums.COMMENT.getType())){
            //回复评论
            Content temp_content = contentMapper.selectByPrimaryKey(content.getId());
            if(temp_content==null){
                throw new CustomException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            contentMapper.insert(content);
        }else {
            //回复问题
            Question question  = questionMapper.selectByPrimaryKey(content.getParentId());
            if(question==null){
                throw new CustomException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            contentMapper.insert(content);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }

    }
}
