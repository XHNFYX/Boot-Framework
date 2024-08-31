package com.bfw.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.exception
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  20:00
 * @Description:
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordException extends CustomException {
    private String exceptionMessage;
}
