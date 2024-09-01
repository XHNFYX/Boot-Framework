package com.bfw.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.dto
 * @Author: 风花雪月
 * @CreateTime: 2024-09-01  18:08
 * @Description:
 * @Version: 1.0
 */
@Data
public class EmployeeDTO implements Serializable {
    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;
}
