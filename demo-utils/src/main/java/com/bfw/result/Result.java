package com.bfw.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.result
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  21:01
 * @Description:响应数据封装类
 * @Version: 1.0
 */
@Data
public class Result<T> implements Serializable {
    private boolean code; //编码
    private String msg; //信息
    private T data; //数据

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(true);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(true);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(true);
        result.setMsg("SUCCESS");
        return result;
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(false);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(false);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<T>();
        result.setCode(false);
        result.setMsg("ERROR");
        return result;
    }
}
