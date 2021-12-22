package com.tangyx.forum.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tangyx
 * @date 2021/11/16 23:14
 * +
 */

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }

}
