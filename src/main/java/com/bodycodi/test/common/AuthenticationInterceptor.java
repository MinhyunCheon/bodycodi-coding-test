package com.bodycodi.test.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bodycodi.test.controller.TokenController;
import com.bodycodi.test.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        // 2022-01-27
        int userId = TokenController.getInstence().getTokenId(token);
        if(userId != 0) {
        	UserDto user = new UserDto();
            user.setId(userId);
            request.setAttribute("user", user);
            
            return true;
        }

        return false;
    }


}
