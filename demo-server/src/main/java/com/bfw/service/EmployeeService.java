package com.bfw.service;

import com.bfw.dto.EmployeeDTO;
import com.bfw.dto.EmployeeLoginDTO;
import com.bfw.dto.QueryPageDTO;
import com.bfw.result.Result;
import javax.servlet.http.HttpServletRequest;

public interface EmployeeService {
     Result selectPage(QueryPageDTO queryPageDTO);
     Result getEmployeeById(Long id);
     Result statusById(Integer status, Long id);
    Result login(EmployeeLoginDTO employeeLoginDTO);
    Result loginInfo(HttpServletRequest request);
    Result logout(HttpServletRequest request);
    Result addEmployee(EmployeeDTO employeeDTO);
    Result updateEmployee(EmployeeDTO employeeDTO);
}
