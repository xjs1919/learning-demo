package com.xjs1919.mybatis.dao;

import com.xjs1919.mybatis.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:50
 */
@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee selectById(@Param("id") Integer id);

    public int update(Employee user);
}
