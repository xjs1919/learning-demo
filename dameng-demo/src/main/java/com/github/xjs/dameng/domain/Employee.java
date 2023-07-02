package com.github.xjs.dameng.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjs
 * @since 2023-07-02
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer employeeId;

    private String employeeName;

    private LocalDate hireDate;

    private Integer salary;

    private Integer departmentId;


}
