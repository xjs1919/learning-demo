package com.github.xjs.dameng;

import com.alibaba.fastjson.JSON;
import com.github.xjs.dameng.domain.Department;
import com.github.xjs.dameng.domain.Employee;
import com.github.xjs.dameng.mapper.DepartmentMapper;
import com.github.xjs.dameng.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@SpringBootTest
public class DaMengApplicationTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DataSource dataSource;

    @Test
    public void testMybatis(){
        List<Employee> employees = employeeMapper.selectAll();
        System.out.println(JSON.toJSONString(employees));
        List<Department> departments = departmentMapper.selectAll();
        System.out.println(JSON.toJSONString(departments));
    }

    @Test
    public void testDatasource() throws Exception{
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from department");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " : " +resultSet.getString(2));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testJdbc() throws Exception{
        Class.forName("dm.jdbc.driver.DmDriver");
        Connection connection = DriverManager.getConnection("jdbc:dm://192.168.137.138:5236", "DM", "dameng123");
        fixEncoding(connection);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from department");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " : " +resultSet.getString(2));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    private static void fixEncoding(Connection connection){
        try{
            Field field = connection.getClass().getDeclaredField("serverEncoding");
            field.setAccessible(true);
            Object serverEncoding = field.get(connection);
            if(serverEncoding instanceof String){
                field.set(connection, "UTF-8");
            }else if(serverEncoding instanceof Charset){
                field.set(connection, StandardCharsets.UTF_8);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}