package com.bfw.controller;

import com.bfw.dto.EmployeeLoginDTO;
import com.bfw.dto.QueryPageDTO;
import com.bfw.result.Result;
import com.bfw.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.controller
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  21:07
 * @Description:员工控制器
 * @Version: 1.0
 */
@Api(tags = "员工控制器")
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("员工分页+模糊+时间")
    @PostMapping("/queryPage")
    public Result userPage(@RequestBody QueryPageDTO queryPageDTO) {
        return this.employeeService.selectPage(queryPageDTO);
    }

    @ApiOperation("员工登录")
    @PostMapping("/login")
    public Result login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        return this.employeeService.login(employeeLoginDTO);
    }

    @ApiOperation("员工信息")
    @PostMapping("/loginInfo")
    public Result loginInfo(HttpServletRequest request) {
        return this.employeeService.loginInfo(request);
    }

    @ApiOperation("员工退出登录")
    @PostMapping("/logut")
    public Result logut(HttpServletRequest request) {
        return this.employeeService.logout(request);
    }
}
