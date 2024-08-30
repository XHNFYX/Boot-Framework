package com.bfw.service;

import com.bfw.dto.QueryPageDTO;
import com.bfw.entity.User;
import com.bfw.result.Result;
import com.bfw.vo.UserVO;

public interface TestService {
    Result selectUserPage(QueryPageDTO queryPageDTO);

    Result<User> userById(Long id);

    Result delById(Long id);

    Result insertUser(UserVO userVO);
}
