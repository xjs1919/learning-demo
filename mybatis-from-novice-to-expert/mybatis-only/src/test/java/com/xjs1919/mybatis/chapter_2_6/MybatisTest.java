package com.xjs1919.mybatis.chapter_2_6;

import com.xjs1919.mybatis.entity.chapter_2_6.Gender;
import com.xjs1919.mybatis.entity.chapter_2_6.User;
import com.xjs1919.mybatis.mapper.chapter_2_6.User2Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;


    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("chapter_2_6/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after() throws Exception {
        if(sqlSession != null){
            sqlSession.close();
        }
    }

    @Test
    public void testDefaultProperties() throws Exception {
        User u = sqlSession.selectOne("userMapper.selectById", 1);
        System.out.println(u);
    }

    @Test
    public void testTypeAlias() throws Exception {
        User u = sqlSession.selectOne("userMapper.selectById", 1);
        System.out.println(u);
    }


    @Test
    public void testEnumOrdinal(){
        User u = new User("xjs", Gender.MALE);
        sqlSession.insert("userMapper.insert", u);
        System.out.println(u);
        sqlSession.commit();
    }

    @Test
    public void testEnumOrdinal2(){
        User u = sqlSession.selectOne("userMapper.selectById", 119);
        System.out.println(u);
    }

    @Test
    public void testEnumName(){
        User u = new User("xjs", Gender.MALE);
        sqlSession.insert("userMapper.insert", u);
        System.out.println(u);
        sqlSession.commit();
    }

    @Test
    public void testCustomEnum(){
        User u = new User("xjs", Gender.MALE);
        sqlSession.insert("userMapper.insert", u);
        System.out.println(u);
        sqlSession.commit();
    }

    @Test
    public void testCustomEnum2(){
        User u = sqlSession.selectOne("userMapper.selectById", 119);
        System.out.println(u);
    }

    @Test
    public void testCustomDatasource(){
        User u = sqlSession.selectOne("userMapper.selectById", 119);
        System.out.println(u);
    }

    @Test
    public void testUseMapper(){
        User2Mapper mapper = sqlSession.getMapper(User2Mapper.class);
        System.out.println(mapper.selectById(1));
    }

    /**
     * file->setting->java compiler, rebuild project
     * https://blog.csdn.net/goldenfish1919/article/details/107123204
     * */
    @Test
    public void testActualParamName(){
        User2Mapper mapper = sqlSession.getMapper(User2Mapper.class);
        System.out.println(mapper.selectByNameAndGender("xjs", 1));
    }

}
