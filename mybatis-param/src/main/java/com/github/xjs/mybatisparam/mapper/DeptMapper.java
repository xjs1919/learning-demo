package com.github.xjs.mybatisparam.mapper;

import com.github.xjs.mybatisparam.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept where name = #{name} and code = #{code}")
    List<Dept> selectByNameAndCode(String name, String code);
}
