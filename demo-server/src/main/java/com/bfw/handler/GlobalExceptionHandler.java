package com.bfw.handler;


import com.bfw.exception.CustomException;
import com.bfw.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*全局处理自定义异常*/
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result bizExceptionHandler(CustomException e) {
        log.error("自定义异常：{}", e.getErrorMsg());
        return Result.error(e.getErrorMsg(), null);
    }

    /*全局处理系统异常*/
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result exceptionHandler(RuntimeException e) {
        log.error("系统异常：{}",e.getMessage());
        return Result.error(e.getMessage(), null);
    }
}

