package com.github.xjs.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class User {
    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
