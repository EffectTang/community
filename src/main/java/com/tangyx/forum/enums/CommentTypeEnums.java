package com.tangyx.forum.enums;

/**
 * @author tangyx
 * @date 2021/12/15 22:52
 * +
 */
public enum CommentTypeEnums {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    CommentTypeEnums(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnums commentTypeEnums:CommentTypeEnums.values()) {
            if(commentTypeEnums.getType().equals(type))
                return true;
        }
        return false;
    }

    public Integer getType(){
        return this.type;
    }

}
