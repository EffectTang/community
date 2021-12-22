package com.tangyx.forum.dto;

import lombok.Data;

/**
 * @author tangyx
 * @date 2021/11/25 22:43
 * +
 */


@Data
public class ContentDTO {

    private String content;
    private Long parentId;
    private Integer type;

}
