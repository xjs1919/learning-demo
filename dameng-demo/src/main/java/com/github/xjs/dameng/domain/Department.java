package com.github.xjs.dameng.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjs
 * @since 2023-07-02
 */
@Data
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer departmentId;

    private String departmentName;


}
