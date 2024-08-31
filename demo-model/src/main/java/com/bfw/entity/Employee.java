package com.bfw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.entity
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  19:14
 * @Description:
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;



}
