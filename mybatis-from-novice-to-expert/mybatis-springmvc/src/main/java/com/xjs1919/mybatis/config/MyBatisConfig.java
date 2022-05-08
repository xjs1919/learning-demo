package com.xjs1919.mybatis.config;

import com.xjs1919.mybatis.dao.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 15:02
 */

@Configuration
@EnableTransactionManagement
//@MapperScan(basePackages="com.xjs1919.mybatis.dao",
//        sqlSessionFactoryRef = "sqlSessionFactory",
//        annotationClass = Mapper.class)
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        Resource[] mappers = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml");
        factoryBean.setMapperLocations(mappers);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * 以下三种方式都可以实现单个Mapper注入到Spring容器
     * */
//    @Bean
//    public UserMapper userMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
//        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
//        factoryBean.setSqlSessionFactory(sqlSessionFactory);
//        return factoryBean.getObject();
//    }

//    @Bean
//    public MapperFactoryBean<UserMapper> userMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
//        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
//        factoryBean.setSqlSessionFactory(sqlSessionFactory);
//        return factoryBean;
//    }

//    @Bean
//    public UserMapper userMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
//        return sqlSessionTemplate.getMapper(UserMapper.class);
//    }

    /**
     * 自动扫描Mapper，注入到Spring容器，或者启用MapperScan注解
     * */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xjs1919.mybatis.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }

    /**
     * 事务管理器，注意同时要启用@EnableTransactionManagement
     * */
    @Bean
    public TransactionManager transactionManager(DataSource ds){
        return new DataSourceTransactionManager(ds);
    }

}
