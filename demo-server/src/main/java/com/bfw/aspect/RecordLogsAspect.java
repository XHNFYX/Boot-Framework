package com.bfw.aspect;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bfw.constant.HttpConstant;
import com.bfw.entity.OperateLog;
import com.bfw.mapper.OperateLogMapper;
import com.bfw.properties.JwtProperties;
import com.bfw.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.aspect
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  14:13
 * @Description:日志切面类
 * @Version: 1.0
 */
@Slf4j
@Component
@Aspect
public class RecordLogsAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.bfw.annotate.RecordLogs)")
    public Object recordLogs(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime localDateTime = LocalDateTime.now();
        //        记录操作耗时-开始
        long begin = System.currentTimeMillis();
        //        执行原始方法
        Object object = joinPoint.proceed();
        //        记录操作耗时-结束
        long end = System.currentTimeMillis();
        new OperateLog();
        OperateLog operateLog = OperateLog.builder()
                //        记录操作时间
                .operateTime(localDateTime)
                //        获取请求头的authorization中的用户ID
//                .operateUser((Long) JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), request.getHeader(HttpConstant.TOKEN)).get("id"))
                .operateUser(1L)
                //        记录操作类名
                .className(joinPoint.getTarget().getClass().getName())
                //        记录操作方法
                .methodName(joinPoint.getSignature().getName())
                //        记录方法参数
                .methodParams(Arrays.toString(joinPoint.getArgs()))
                //        记录方法返回值
                .returnValue(JSONUtil.toJsonStr(object.toString()))
                //        记录操作耗时
                .costTime(end-begin)
                .build();
        int i = this.operateLogMapper.insert(operateLog);
        log.info("操作日志记录(大于0记录成功)：{}", i);
        return object;
    }
}
