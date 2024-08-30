package com.bfw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.entity
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  14:04
 * @Description:
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;//ID
    private Long operateUser;//操作人ID
    private LocalDateTime operateTime;//操作时间
    private String className;//操作类名
    private String methodName; //操作方法名
    private String methodParams;//操作方法参数
    private String returnValue;//操作方法的返回值
    private Long costTime;//操作耗时
}

