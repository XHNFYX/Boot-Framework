package com.bfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper extends BaseMapper<User> {
    IPage<User> queryPage(IPage<User> iPage, @Param("vo") QueryPageDTO queryPageDTO);
}
