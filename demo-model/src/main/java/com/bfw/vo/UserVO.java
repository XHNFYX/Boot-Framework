package com.bfw.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.vo
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  23:11
 * @Description:
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String username;
    private String password;
}
