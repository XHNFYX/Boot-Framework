package com.bfw.controller;

import com.bfw.entity.User;
import com.bfw.result.Result;
import com.bfw.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.controller
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  20:15
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/{id}")
    public Result<User> test(@PathVariable Long id) {
        return this.testService.userById(id);
    }
}
