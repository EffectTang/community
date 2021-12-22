package com.tangyx.forum.mapper;

import com.tangyx.forum.model.Content;

/**
 * @author tangyx
 * @date 2021/12/19 23:31
 * +
 */
public interface CommentExtMapper {

    int incCommentCount(Content comment);
}
