package com.xjs1919.mybatis.shard;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:20
 */
@Slf4j
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfig implements BeanFactoryAware {

    private BeanFactory beanFactory;
    private List<String> basePackages;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException{
        this.beanFactory = beanFactory;
        this.basePackages = AutoConfigurationPackages.get(this.beanFactory);
    }

    @Bean
    public static DataSource dataSource(@Qualifier("dataSource1") DataSource dataSource1,
                                 @Qualifier("dataSource2") DataSource dataSource2){
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DataSourceEnum.SHARD0, dataSource1);
        targetDataSources.put(DataSourceEnum.SHARD1, dataSource2);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSource1);
        return dynamicDataSource;
    }

    @Bean
    public DataSource dataSource1(DataSourceProperties dataSourceProperties)throws Exception{
        DataSourceProperties.DSProperties dsProperties = dataSourceProperties.getShard0();
        return dataSource(dsProperties, "dataSource1");
    }

    @Bean
    public DataSource dataSource2(DataSourceProperties dataSourceProperties)throws Exception{
        DataSourceProperties.DSProperties dsProperties = dataSourceProperties.getShard1();
        return dataSource(dsProperties, "dataSource2");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        List<Resource> mapperList = new ArrayList<Resource>();
        for(String basePackage : basePackages){
            String location = "classpath*:" + basePackage.replace(".", "/") + "/**/*.xml";
            log.info("mapperLocation:{}", location);
            Resource[] mappers = new PathMatchingResourcePatternResolver().getResources(location);
            if(mappers != null && mappers.length > 0){
                for(Resource mapper : mappers){
                    mapperList.add(mapper);
                }
            }
        }
        sqlSessionFactoryBean.setMapperLocations(mapperList.toArray(new Resource[0]));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        mapperScannerConfigurer.setBasePackage(StringUtils.collectionToCommaDelimitedString(basePackages));
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource")DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    private DataSource dataSource(DataSourceProperties.DSProperties dsProperties, String uniqueName)throws Exception{
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dsProperties.getUrl());
        ds.setUsername(dsProperties.getUsername());
        ds.setPassword(dsProperties.getPassword());
        return ds;
    }

}
