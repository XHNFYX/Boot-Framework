package com.bfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    IPage<Employee> queryPage(IPage<Employee> iPage, @Param("vo") QueryPageDTO queryPageDTO);
}
