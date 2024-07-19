package com.github.xjs.sbdemo.mapper;

import com.github.xjs.sbdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:00:52<br/>
 */
@Mapper
public interface UserMapper {
	public int isTableExist();
	public void createTable();
	public void dropTable();
	public int insert(User user);
	public User selectById(Integer id);
	public List<User> listAll();
}
