package com.bfw.service;

import com.bfw.entity.User;
import com.bfw.result.Result;

public interface TestService {
    Result<User> userById(Long id);
}
