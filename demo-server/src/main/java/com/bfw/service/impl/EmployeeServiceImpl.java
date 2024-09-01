package com.bfw.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfw.constant.*;
import com.bfw.dto.EmployeeDTO;
import com.bfw.dto.EmployeeLoginDTO;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.Employee;
import com.bfw.exception.CustomException;
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
import java.util.concurrent.TimeUnit;

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

    /**依赖注入-开始**/
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmployeeMapper employeeMapper;
    /**依赖注入-结束**/

    @Override
    public Result selectPage(QueryPageDTO queryPageDTO) {
        //        分页对象
        IPage<Employee> iPage = new Page<>(queryPageDTO.getPage(), queryPageDTO.getLimit());
        //        分页查询
        IPage<Employee> employeeIPage = baseMapper.queryPage(iPage, queryPageDTO);
        return Result.success("OK", employeeIPage);
    }

    @Override
    public Result getEmployeeById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        employee.setPassword("******");
        return Result.success(employee);
    }

    @Override
    public Result statusById(Integer status, Long id) {
        Employee employee = Employee.builder().status(status).id(id).build();
        int i = employeeMapper.updateById(employee);
        if (i < 1) {
            return Result.error();
        }
        return Result.success();
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
//            warning 缓存空值，防止缓存穿透问题
            stringRedisTemplate.opsForValue()
                    .set(
                            RedisConstants.LOGIN_USER_KEY + username,
                            "",
                            RedisConstants.LOGIN_CODE_TTL, TimeUnit.MINUTES);
            throw new CustomException(LoginConstant.USER_DOES_NOT_EXIST);
        }
//       密码错误
        if (!password.equals(employee.getPassword())) {
            throw new CustomException(LoginConstant.PASSWORD_ERROR);
        }
//        该用户已停用
        if (employee.getStatus() == 0) {
            throw new CustomException(LoginConstant.USER_DEACTIVATED);
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
        //        缓存到Redis中，并设置Redis缓存时间
        stringRedisTemplate.opsForValue()
                .set(
                        RedisConstants.LOGIN_USER_KEY + employee.getId(),
                        JSONUtil.toJsonStr(employeeLoginVO),
                        RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES
                );
        return Result.success(token);
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
        return Result.success(employeeLoginVO);
    }

    @Override
    public Result logout(HttpServletRequest request) {
        //        获取token并解析
        String token = request.getHeader(HttpConstant.ADMIN_TOKEN);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
//        从Redis缓存中删除该用户信息
        stringRedisTemplate.delete(RedisConstants.LOGIN_USER_KEY + empId);
        return Result.success();
    }

    @Override
    public Result addEmployee(EmployeeDTO employeeDTO) {
//        已从前端确认数据合法
        Employee employee = BeanUtil.copyProperties(employeeDTO, Employee.class);
        employee.setPassword(PasswordConstant.DEFAULT_PASSWORD);
        boolean b = save(employee);
        if (!b) {
            throw new CustomException(UsernameConstant.SAVE_USERNAME_ERROR);
        }
        return Result.success();
    }

    @Override
    public Result updateEmployee(EmployeeDTO employeeDTO) {
        //        已从前端确认数据合法
        Employee employee = BeanUtil.copyProperties(employeeDTO, Employee.class);
        log.info("employee:{}", JSONUtil.toJsonStr(employee));
        boolean b = updateById(employee);
        if (!b) {
            throw new CustomException(UsernameConstant.UPDATE_USERNAME_ERROR);
        }
        return Result.success();
    }
}
