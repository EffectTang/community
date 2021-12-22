package com.tangyx.forum.controller;

import com.tangyx.forum.dto.ContentDTO;
import com.tangyx.forum.dto.ResultDTO;
import com.tangyx.forum.exception.CustomizeErrorCode;
import com.tangyx.forum.mapper.ContentMapper;
import com.tangyx.forum.model.Content;
import com.tangyx.forum.model.User;
import com.tangyx.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangyx
 * @date 2021/11/25 22:37
 * +
 */
@Controller
public class CommentControlller {

    @Autowired
    ContentMapper contentMapper;

    @Autowired
    CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object putComment(@RequestBody ContentDTO contentDTO,
                             HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.valueOf(CustomizeErrorCode.NO_LOGIN);
        }
        Content content = new Content();
        content.setParentId(contentDTO.getParentId());

        content.setCommentator(user.getId());
        content.setType(contentDTO.getType());
        content.setContent(contentDTO.getContent());

        content.setGmtCreate(System.currentTimeMillis());
        content.setGmtModified(System.currentTimeMillis());
        content.setCommentator(user.getId());

        //因为是likeCount是Long类型
        content.setLikeCount(0L);
        commentService.insert(content);
        return ResultDTO.isOk();
    }
}
