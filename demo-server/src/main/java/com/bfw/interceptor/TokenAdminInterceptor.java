package com.bfw.interceptor;

import com.bfw.constant.JwtClaimsConstant;
import com.bfw.context.BaseContext;
import com.bfw.enumeration.HttpEnum;
import com.bfw.properties.JwtProperties;
import com.bfw.result.Result;
import com.bfw.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.service.impl
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  20:59
 * @Description: admin端token拦截器
 * @Version: 1.0
 */
@Component
@Slf4j
public class TokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        //2、校验令牌
        try {
            log.info("jwt校验:{}", jwtProperties.getAdminSecretKey());
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：{}", empId);
//            将当前员工的id存储到ThreadLocal的独立空间中
            BaseContext.setCurrentId(empId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(Result.error(HttpEnum.UNAUTHORIZED.getMessage(), null));
            response.getWriter().print(result);
            return false;
        }
    }
}