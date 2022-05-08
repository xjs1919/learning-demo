package com.xjs1919.mybatis.domain;

import lombok.Data;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:29
 */
@Data
public class BaseEntity {

    private String tenantId;

    private Integer groupId;

    private Integer companyId;

    private Integer id;

}
