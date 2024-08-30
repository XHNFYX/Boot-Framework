package com.bfw.controller;

import com.bfw.annotate.RecordLogs;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.User;
import com.bfw.exception.CustomException;
import com.bfw.result.Result;
import com.bfw.service.TestService;
import com.bfw.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "测试控制器")
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation("分页查询")
    @PostMapping("/queryPage")
    public Result userPage(@RequestBody QueryPageDTO queryPageDTO) {
        return this.testService.selectUserPage(queryPageDTO);
    }

    @ApiOperation(("获取user"))
    @GetMapping("/getUser/{id}")
    public Result<User> test(@PathVariable Long id) {
        return this.testService.userById(id);
    }

    @RecordLogs
    @ApiOperation("添加user")
    @PostMapping("insert")
    public Result insertUser(@RequestBody UserVO userVO) {
        return this.testService.insertUser(userVO);
    }

    @RecordLogs
    @ApiOperation("删除user")
    @DeleteMapping("/delete/{id}")
    public Result del(@PathVariable Long id) {
        return this.testService.delById(id);
    }

    @ApiOperation("自定义异常")
    @PostMapping("/custom")
    public Result custom() {
        if (true) {
            throw new CustomException("自定义异常");
        }
        return Result.success();
    }

    @ApiOperation("系统异常")
    @PostMapping("/system")
    public Result system() {
        int i = 5 / 0;
        return Result.success();
    }
}
