package com.bfw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.dto
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  11:11
 * @Description:
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    //    页码
    private Long page;
    //    每页数量
    private Long limit;
    //    模糊索引
    private String keyword;
    //    开始时间
    private String createTimeBegin;
    //    结束时间
    private String createTimeEnd;
}
