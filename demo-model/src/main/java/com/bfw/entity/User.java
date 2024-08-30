package com.bfw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.entity
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  20:45
 * @Description:测试实体类
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long UserId;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;
}