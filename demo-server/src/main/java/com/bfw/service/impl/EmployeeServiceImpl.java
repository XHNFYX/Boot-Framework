package com.bfw.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfw.constant.HttpConstant;
import com.bfw.constant.JwtClaimsConstant;
import com.bfw.constant.LoginConstant;
import com.bfw.constant.RedisConstants;
import com.bfw.dto.EmployeeLoginDTO;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.Employee;
import com.bfw.exception.PasswordException;
import com.bfw.exception.UsernameException;
import com.bfw.mapper.EmployeeMapper;
import com.bfw.properties.JwtProperties;
import com.bfw.result.Result;
import com.bfw.service.EmployeeService;
import com.bfw.utils.JwtUtil;
import com.bfw.vo.EmployeeLoginVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.service.impl
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  19:12
 * @Description:
 * @Version: 1.0
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Result selectPage(QueryPageDTO queryPageDTO) {
        //        分页对象
        IPage<Employee> iPage = new Page<>(queryPageDTO.getPage(), queryPageDTO.getLimit());
//        分页查询
        IPage<Employee> employeeIPage = baseMapper.queryPage(iPage, queryPageDTO);
        return Result.success("OK", employeeIPage);
    }

    @Override
    public Result login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
//        根据用户名查询用户信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, username);
        Employee employee = this.baseMapper.selectOne(queryWrapper);
//        该用户名不存在
        if (employee == null) {
            throw new UsernameException(LoginConstant.USER_DOES_NOT_EXIST);
        }
//       密码错误
        if (!password.equals(employee.getPassword())) {
            throw new PasswordException(LoginConstant.PASSWORD_ERROR);
        }
//        该用户已停用
        if (employee.getStatus() == 0) {
            throw new UsernameException(LoginConstant.USER_DEACTIVATED);
        }
//       使用用户ID生成token
        Map<String, Object> map = new HashMap<>();
        map.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), map);
//       封装用户数据
        new EmployeeLoginVO();
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .userName(employee.getUsername())
                .token(token).build();
        //        缓存到Redis中
        stringRedisTemplate.opsForValue().set(RedisConstants.LOGIN_USER_KEY + employee.getId(), JSONUtil.toJsonStr(employeeLoginVO));
        return Result.success(HttpConstant.SUCCESS, token);
    }

    @Override
    public Result loginInfo(HttpServletRequest request) {
        //        获取token并解析
        String token = request.getHeader(HttpConstant.ADMIN_TOKEN);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
        log.info("empId:{}", empId);
        String info = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_KEY + empId);
        EmployeeLoginVO employeeLoginVO = JSONUtil.toBean(info, EmployeeLoginVO.class);
        log.info("info:{}", info);
        return Result.success(HttpConstant.SUCCESS, employeeLoginVO);
    }

    @Override
    public Result logout(HttpServletRequest request) {
        //        获取token并解析
        String token = request.getHeader(HttpConstant.ADMIN_TOKEN);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
//        从Redis缓存中删除该用户信息
        stringRedisTemplate.delete(RedisConstants.LOGIN_USER_KEY + empId);
        return Result.success(HttpConstant.SUCCESS, null);
    }



}
