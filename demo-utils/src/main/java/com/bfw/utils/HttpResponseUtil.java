package com.bfw.utils;

import com.bfw.enumeration.HttpEnum;
import com.bfw.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.utils
 * @Author: 风花雪月
 * @CreateTime: 2024-09-01  16:45
 * @Description:
 * @Version: 1.0
 */
public class HttpResponseUtil {
    public static void response(HttpServletResponse response, HttpEnum httpEnum) throws IOException {
        Integer code = httpEnum.getCode();
        String message = httpEnum.getMessage();
        response.setStatus(code);
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(Result.error(message));
        response.getWriter().print(result);
    }


}
