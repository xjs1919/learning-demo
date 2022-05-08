package com.xjs1919.mybatis.chapter_2_1;

import com.xjs1919.mybatis.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 若鱼1919
 * @date 2022/02/02 11:14
 */
public class JdbcTest {
    public static void main(String[] args) throws Exception{
        // 注册驱动
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mybatis_from_novice_to_expert?useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "123456");
        // SQL预编译
        PreparedStatement stmt = conn.prepareStatement("select * from users where id = ?");
        // 填充参数
        stmt.setInt(1, 1);
        // 执行查询
        ResultSet rs = stmt.executeQuery();
        // 读取结果集，组装结果
        User user = new User();
        while(rs.next()){
            user.setId(rs.getInt(1));
            user.setName(rs.getString("name"));
            user.setGender(rs.getInt(3));
            user.setBirth(rs.getDate(4));
        }
        rs.close();
        stmt.close();
        conn.close();
        // 打印
        System.out.println(user);
    }
}
