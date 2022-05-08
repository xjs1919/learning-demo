package com.xjs1919.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:20
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class MultiDataSourceConfig {

    @Bean
    public DataSource dataSource1(DataSourceProperties dataSourceProperties)throws Exception{
        DataSourceProperties.DSProperties dsProperties = dataSourceProperties.getDs1();
        return dataSource(dsProperties, "dataSource1");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1")DataSource dataSource1) throws Exception{
        return sqlSessionFactory("classpath:com/xjs1919/mybatis/dao/ds1/*.xml", dataSource1);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("dataSource1")DataSource dataSource1)throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory1(dataSource1));
        return sqlSessionTemplate;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer1(){
        return mapperScannerConfigurer("sqlSessionFactory1", "com.xjs1919.mybatis.dao.ds1");
    }

    @Bean
    public DataSource dataSource2(DataSourceProperties dataSourceProperties)throws Exception{
        DataSourceProperties.DSProperties dsProperties = dataSourceProperties.getDs2();
        return dataSource(dsProperties, "dataSource2");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2")DataSource dataSource2)throws Exception{
        return sqlSessionFactory("classpath:com/xjs1919/mybatis/dao/ds2/*.xml", dataSource2);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("dataSource2")DataSource dataSource2)throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory2(dataSource2));
        return sqlSessionTemplate;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer2(){
        return mapperScannerConfigurer("sqlSessionFactory2", "com.xjs1919.mybatis.dao.ds2");
    }

//    @Bean
//    public DataSourceTransactionManager transactionManager1(@Qualifier("dataSource1")DataSource dataSource){
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager2(@Qualifier("dataSource2")DataSource dataSource){
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }

//    private DataSource dataSource(DataSourceProperties.DSProperties dsProperties, String uniqueName)throws Exception{
//        DruidDataSource ds = new DruidDataSource();
//        ds.setUrl(dsProperties.getUrl());
//        ds.setUsername(dsProperties.getUsername());
//        ds.setPassword(dsProperties.getPassword());
//        return ds;
//    }

    private DataSource dataSource(DataSourceProperties.DSProperties dsProperties, String uniqueName)throws Exception{
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dsProperties.getUrl());
        mysqlXaDataSource.setUser(dsProperties.getUsername());
        mysqlXaDataSource.setPassword(dsProperties.getPassword());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(uniqueName);
        return xaDataSource;
    }

    private SqlSessionFactory sqlSessionFactory(String mapperLocation, DataSource ds) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        Resource[] mappers = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
        sqlSessionFactoryBean.setMapperLocations(mappers);
        sqlSessionFactoryBean.setDataSource(ds);
        return sqlSessionFactoryBean.getObject();
    }

    private MapperScannerConfigurer mapperScannerConfigurer(String sqlSessionFactoryBeanName, String basePackage){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(sqlSessionFactoryBeanName);
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }

}
