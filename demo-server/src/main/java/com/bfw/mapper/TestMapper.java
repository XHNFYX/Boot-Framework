package com.bfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bfw.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<User> {
}
