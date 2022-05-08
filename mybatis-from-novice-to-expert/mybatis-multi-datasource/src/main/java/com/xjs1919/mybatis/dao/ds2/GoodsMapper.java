package com.xjs1919.mybatis.dao.ds2;

import com.xjs1919.mybatis.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:51
 */
@Mapper
public interface GoodsMapper {

    @Select("select * from goods where id = #{id}")
    public Goods selectById(@Param("id")Integer id);

    public int update(Goods goods);
}
