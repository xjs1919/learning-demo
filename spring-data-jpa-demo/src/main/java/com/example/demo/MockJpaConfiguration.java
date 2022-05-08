package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.mock.RepositoryFactoryBean;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 10:03
 */
@Configuration
@EnableTransactionManagement
// @EnableJpaRepositories("com.example.demo.dao")
@ComponentScan(basePackages = "com.example.demo",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JpaConfiguration.class})})
public class MockJpaConfiguration {

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/jpa_demo?allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        Properties props = new Properties();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.example.demo.entity");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(props);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    public static class SpringPhysicalNamingStrategy implements PhysicalNamingStrategy {
        public SpringPhysicalNamingStrategy() {
        }

        public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
            return this.apply(name, jdbcEnvironment);
        }

        public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
            return this.apply(name, jdbcEnvironment);
        }

        public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
            return this.apply(name, jdbcEnvironment);
        }

        public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
            return this.apply(name, jdbcEnvironment);
        }

        public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
            return this.apply(name, jdbcEnvironment);
        }

        private Identifier apply(Identifier name, JdbcEnvironment jdbcEnvironment) {
            if (name == null) {
                return null;
            } else {
                StringBuilder builder = new StringBuilder(name.getText().replace('.', '_'));

                for(int i = 1; i < builder.length() - 1; ++i) {
                    if (this.isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i), builder.charAt(i + 1))) {
                        builder.insert(i++, '_');
                    }
                }

                return this.getIdentifier(builder.toString(), name.isQuoted(), jdbcEnvironment);
            }
        }

        protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
            if (this.isCaseInsensitive(jdbcEnvironment)) {
                name = name.toLowerCase(Locale.ROOT);
            }

            return new Identifier(name, quoted);
        }

        protected boolean isCaseInsensitive(JdbcEnvironment jdbcEnvironment) {
            return true;
        }

        private boolean isUnderscoreRequired(char before, char current, char after) {
            return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
        }
    }
}
