package com.xjs1919.mybatis.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.util.Date;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:28
 */
@Data
public class Employee extends BaseEntity {

    private String name;
    private Integer gender;
    private Date birth;
    private Date entryTime;
    private String mobile;

}
