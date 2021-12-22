package com.tangyx.forum.exception;

/**
 * @author tangyx
 * @date 2021/12/9 23:00
 * +
 */
public enum CustomizeErrorCode implements BasicExceptionCode {

    QUESTION_NOT_FOUND (2001,"这个问题，已经被删除了，不好意思"),
    QUESTION_UPDADE_NOT_FOUND(2002,"在你更新的期间，这个问题不知道被谁删除了，很抱歉"),
    NO_LOGIN(2030,"对不起，您未登录，请先登录"),
    SYSTEM_ERROR(2009,"系统出了点小毛病,稍等"),
    COMMENT_PARENT_NOT(2011,"评论类型id错误，或问题不存在了"),
    COMMENT_NOT_FOUND(2012,"评论已经被删除了,无法再进行评论了"),

    COMMENT_NOT_PARENT_ID(2010,"未选中任何问题或评论进行评价，请先选中");


    Integer code;
    String message;

    CustomizeErrorCode(Integer code,String s) {
        this.message = s;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
    @Override
    public String getMessage() {
        return this.message;
    }

}
