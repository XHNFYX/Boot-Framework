package com.bfw.context;


/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.config
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  21:33
 * @Description:用户ID上下文存取
 * @Version: 1.0
 */
public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
