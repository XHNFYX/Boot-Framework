package com.bfw.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.handler
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  23:29
 * @Description:
 * @Version: 1.0
 */
@Component
public class MyBatisMateObjectHandler implements MetaObjectHandler {
    //    配置一个或多个需要插入填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    //    配置一个或多个需要更新的填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
