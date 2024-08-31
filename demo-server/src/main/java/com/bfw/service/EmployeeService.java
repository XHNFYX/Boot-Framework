package com.bfw.service;

import com.bfw.dto.EmployeeLoginDTO;
import com.bfw.dto.QueryPageDTO;
import com.bfw.result.Result;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService {
    public Result selectPage(QueryPageDTO queryPageDTO);
    Result login(EmployeeLoginDTO employeeLoginDTO);
    Result loginInfo(HttpServletRequest request);
    Result logout(HttpServletRequest request);
}
