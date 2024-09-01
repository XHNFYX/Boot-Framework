package com.bfw.interceptor;

import cn.hutool.core.util.StrUtil;
import com.bfw.constant.JwtClaimsConstant;
import com.bfw.constant.RedisConstants;
import com.bfw.context.BaseContext;
import com.bfw.enumeration.HttpEnum;
import com.bfw.properties.JwtProperties;
import com.bfw.utils.HttpResponseUtil;
import com.bfw.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

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
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long empId = null;
        log.info("请求URL：{}", request.getRequestURL());
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());
//        2.token为空，拒绝访问
        if (StrUtil.isBlank(token)) {
            log.info("token为空");
            HttpResponseUtil.response(response, HttpEnum.UNAUTHORIZED);
            return false;
        }
//          3.解析token
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：{}", empId);
            //        4.将该用户ID存入ThreadLocal中
            BaseContext.setCurrentId(empId);
        } catch (Exception e) {
//            校验出现异常，默认非法token
            HttpResponseUtil.response(response, HttpEnum.ILLEGAL_AUTHENTICATION);
            return false;
        }
//        如果正常获取为空
        if (StrUtil.isBlank(empId.toString())) {
            //            校验出现异常，默认非法token
            HttpResponseUtil.response(response, HttpEnum.ILLEGAL_AUTHENTICATION);
            return false;
        }
//        5.Redis中的信息已经过期
        String info = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_KEY + empId);
        log.info("info:{}", info);
        if (StrUtil.isBlank(info) || "null".equals(info)) {
            HttpResponseUtil.response(response, HttpEnum.AUTHENTICATION_EXPIRE);
            return false;
        }
//        6.更新Redis缓存时间
        stringRedisTemplate.expire(RedisConstants.LOGIN_USER_KEY + empId, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        log.info("最后放行，key：{}", RedisConstants.LOGIN_USER_KEY + empId);
        return true;
    }
}