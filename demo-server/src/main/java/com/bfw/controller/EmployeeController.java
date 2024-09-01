package com.bfw.controller;

import com.bfw.dto.EmployeeDTO;
import com.bfw.dto.EmployeeLoginDTO;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.Employee;
import com.bfw.result.Result;
import com.bfw.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("员工分页+模糊+时间")
    @PostMapping("/queryPage")
    public Result userPage(@RequestBody QueryPageDTO queryPageDTO) {
        log.info("分页查询:{}", queryPageDTO);
        return this.employeeService.selectPage(queryPageDTO);
    }

    @ApiOperation("员工登录")
    @PostMapping("/login")
    public Result login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录:{}", employeeLoginDTO);
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

    @ApiOperation("根据ID获取用户信息")
    @GetMapping("/{id}")
    public Result getEmployeeById(@PathVariable Long id) {
        log.info("员工ID：{}", id);
        return this.employeeService.getEmployeeById(id);
    }

    @ApiOperation("根据ID更新用户状态")
    @PostMapping("/status")
    public Result updateStatus(@RequestBody EmployeeLoginDTO employeeDTO) {
        Long id = employeeDTO.getId();
        Integer status = employeeDTO.getStatus();
        log.info("更新状态：{}", id, status > 0 ? "启用" : "禁用");
        return this.employeeService.statusById(status, id);
    }

    @ApiOperation("更新员工信息")
    @PutMapping
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        return this.employeeService.updateEmployee(employeeDTO);
    }

    @ApiOperation("新增员工")
    @PostMapping
    public Result insert(@RequestBody EmployeeDTO employeeDTO) {
        return this.employeeService.addEmployee(employeeDTO);
    }
}
