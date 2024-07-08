package com.laoayu.parking.interceptor;

import com.alibaba.fastjson2.JSON;
import com.laoayu.parking.common.holder.UserHolder;
import com.laoayu.parking.common.utils.JwtUtil;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.User;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: LaoAyu
 * @date: 2023/03/10
 **/

//Jwt验证拦截器
@Component
@Slf4j
public class JwtValidateInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("P-Token");
        log.debug(request.getRequestURI() +" 待验证："+token);
        if(token != null){
            try {
//                jwtUtil.parseToken(token);
                User user = jwtUtil.parseToken(token, User.class);
                UserHolder.saveUser(user);
                log.debug(request.getRequestURI() +" 验证通过");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug(request.getRequestURI() +" 验证失败，禁止访问");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.fail(20003,"jwt令牌无效，请重新登录")));
        return false; //拦截
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        UserHolder.removeUser();
    }
}
