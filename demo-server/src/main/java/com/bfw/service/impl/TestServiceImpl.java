package com.bfw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfw.entity.User;
import com.bfw.mapper.TestMapper;
import com.bfw.result.Result;
import com.bfw.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.service.impl
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  20:59
 * @Description: 业务测试类
 * @Version: 1.0
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, User> implements TestService {
    @Override
    public Result<User> userById(Long id) {
        return Result.success("OK", this.baseMapper.selectById(id));
    }
}
