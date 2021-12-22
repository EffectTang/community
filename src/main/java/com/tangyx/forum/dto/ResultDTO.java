package com.tangyx.forum.dto;

import com.tangyx.forum.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @author tangyx
 * @date 2021/12/15 22:43
 * +
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO valueOf(CustomizeErrorCode code){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code.getCode());
        resultDTO.setMessage(code.getMessage());
        return resultDTO;
    }

    public static ResultDTO isOk(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO isMistaken(CustomizeErrorCode code){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code.getCode());
        resultDTO.setMessage(code.getMessage());
        return resultDTO;
    }
}
