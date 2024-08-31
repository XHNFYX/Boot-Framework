package com.bfw.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.entity
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  22:42
 * @Description:实体类父类
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity{

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @TableField(value = "status")
    private Integer status;

    @TableLogic
    private int isDeleted;
}
