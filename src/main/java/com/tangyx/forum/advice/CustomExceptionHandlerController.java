package com.tangyx.forum.advice;

import com.alibaba.fastjson.JSON;
import com.tangyx.forum.dto.ResultDTO;
import com.tangyx.forum.exception.CustomException;
import com.tangyx.forum.exception.CustomizeErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tangyx
 * @date 2021/12/8 22:47
 * +
 */

@ControllerAdvice
public class CustomExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Model model, Throwable ex,
                                     HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        String app = "application/json";
        if(app.equals(request.getContentType())){
            ResultDTO resultDTO;
            //返回json 用于回答 不返回页面
            if(ex instanceof CustomException){
                resultDTO = ResultDTO.isMistaken(CustomizeErrorCode.COMMENT_NOT_PARENT_ID);
            }else {
                resultDTO = ResultDTO.isMistaken(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else {
            if(ex instanceof CustomException){
                model.addAttribute("message",ex.getMessage());
            }else {
                model.addAttribute("message","小姐姐来了，我需要先去帮助她");
            }
            return new ModelAndView("error");
        }

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
