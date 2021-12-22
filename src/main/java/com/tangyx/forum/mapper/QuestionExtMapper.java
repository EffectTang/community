package com.tangyx.forum.mapper;

import com.tangyx.forum.model.Question;

/**
 * @author tangyx
 * @date 2021/12/19 23:31
 * +
 */
public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

}
