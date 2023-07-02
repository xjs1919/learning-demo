package com.github.xjs.dameng.mapper;

import com.github.xjs.dameng.domain.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xjs
 * @since 2023-07-02
 */
@Mapper
public interface DepartmentMapper {

    List<Department> selectAll();
}
