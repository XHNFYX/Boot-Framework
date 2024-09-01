package com.bfw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.dto
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  19:09
 * @Description:
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginDTO {
    private Long id;
    private Integer status;
    private String username;
    private String password;
}
