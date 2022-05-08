package com.xjs1919.mybatis.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 若鱼1919
 * @date 2022/3/30 0030 12:07
 */
@Configuration
public class MybatisConfig {

    /**
     * 定制SqlSessionFactory
     * */
//    @Bean
//    public SqlSessionFactoryBeanCustomizer sqlSessionFactoryBeanCustomizer() {
//        return new SqlSessionFactoryBeanCustomizer() {
//            @Override
//            public void customize(SqlSessionFactoryBean sqlSessionFactoryBean) {
//                try{
//                    // 设置mapper
//                    sqlSessionFactoryBean.setMapperLocations();
//                    // 设置TypeHandler
//                    sqlSessionFactoryBean.setTypeHandlers();
//                    // .....
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
//    }

    /**
     * 定制Configuration
     * */
//    @Bean
//    ConfigurationCustomizer mybatisConfigurationCustomizer() {
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(org.apache.ibatis.session.Configuration configuration) {
//                configuration.setMapUnderscoreToCamelCase(true);
//            }
//        };
//    }

//    @Bean
//    public MyInterceptor myInterceptor() {
//        return MyInterceptor();
//    }

//    @Bean
//    public MyTypeHandler myTypeHandler() {
//        return MyTypeHandler();
//    }

//    @Bean
//    public MyLanguageDriver myLanguageDriver() {
//        return MyLanguageDriver();
//    }

//    @Bean
//    public VendorDatabaseIdProvider databaseIdProvider() {
//        VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
//        Properties properties = new Properties();
//        properties.put("SQL Server", "sqlserver");
//        properties.put("DB2", "db2");
//        properties.put("H2", "h2");
//        databaseIdProvider.setProperties(properties);
//        return databaseIdProvider;
//    }

}
