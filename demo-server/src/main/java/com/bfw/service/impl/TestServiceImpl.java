package com.bfw.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.User;
import com.bfw.mapper.TestMapper;
import com.bfw.result.Result;
import com.bfw.service.TestService;
import com.bfw.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result selectUserPage(QueryPageDTO queryPageDTO) {
//        分页对象
        IPage<User> iPage = new Page<>(queryPageDTO.getPage(), queryPageDTO.getLimit());
//        分页查询
        IPage<User> userIPage = baseMapper.queryPage(iPage, queryPageDTO);
        return Result.success("OK", userIPage);
    }

    @Override
    public Result<User> userById(Long id) {
        return Result.success("OK", this.baseMapper.selectById(id));
    }

    @Override
    public Result delById(Long id) {
        if (this.baseMapper.deleteById(id) < 1) {
            return Result.error();
        }
        return Result.success();
    }

    @Override
    public Result insertUser(UserVO userVO) {
        User user = BeanUtil.copyProperties(userVO, User.class);
        if (!save(user)) {
            return Result.error();
        }
        return Result.success();
    }
}
