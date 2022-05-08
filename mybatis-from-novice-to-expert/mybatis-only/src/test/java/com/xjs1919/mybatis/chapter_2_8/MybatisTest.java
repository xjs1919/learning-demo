package com.xjs1919.mybatis.chapter_2_8;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_2_8.UserMapper;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;


    @Test
    public void mybatis() throws Exception{
        // log
        LogFactory.useLog4J2Logging();
        // configuration
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        // datasource
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_from_novice_to_expert?rewriteBatchedStatements=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        // transaction
        Transaction transaction = new JdbcTransaction(dataSource,null,false);
        // executor
        Executor executor = configuration.newExecutor(transaction);
        //sqlSource
        SqlSource sqlSource = new StaticSqlSource(configuration, "select * from users where id = ?");
        MappedStatement.Builder mappedStatementBuilder = new MappedStatement.Builder(configuration,
                "com.xjs1919.mybatis.mapper.chapter_2_8.UserMapper.selectById",sqlSource, SqlCommandType.SELECT);
        // ParameterMap
        List<ParameterMapping> parameterMappings = new ArrayList<>();
        parameterMappings.add(new ParameterMapping.Builder(configuration, "id", Integer.class).build());
        ParameterMap.Builder parameterMapBuild = new ParameterMap.Builder(configuration, "defaultParameterMap", Integer.class, parameterMappings);
        mappedStatementBuilder.parameterMap(parameterMapBuild.build());

        // ResultMap
        List<ResultMap> resultMaps = new ArrayList<>();
        List<ResultMapping> resultMappings = new ArrayList<>();
        resultMappings.add(new ResultMapping.Builder(configuration, "id", "id", Integer.class).build());
        resultMappings.add(new ResultMapping.Builder(configuration, "name", "name", String.class).build());
        resultMappings.add(new ResultMapping.Builder(configuration, "gender", "gender", Integer.class).build());
        ResultMap resultMap = new ResultMap.Builder(configuration, "defaultResultMap", User.class, resultMappings).build();
        resultMaps.add(resultMap);
        mappedStatementBuilder.resultMaps(resultMaps);

        //mappedStatement
        MappedStatement mappedStatement = mappedStatementBuilder.build();

        //直接使用executor
        // List<User> users = executor.query(mappedStatement, 1, RowBounds.DEFAULT, null);

        // 使用sqlSession
        configuration.addMappedStatement(mappedStatement);
        SqlSession sqlSession = new DefaultSqlSession(configuration, executor, false);
        // List<User> users = sqlSession.selectList("com.xjs1919.mybatis.mapper.chapter_2_8.UserMapper.selectById", 1);

        //使用Mapper
        MapperProxyFactory<UserMapper> mapperMapperProxyFactory = new MapperProxyFactory<>(UserMapper.class);
        UserMapper userMapper = mapperMapperProxyFactory.newInstance(sqlSession);
        List<User> users = userMapper.selectById(1);
        System.out.println(users);
    }
}
