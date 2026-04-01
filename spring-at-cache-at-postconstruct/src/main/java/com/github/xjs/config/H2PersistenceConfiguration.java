package com.github.xjs.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = com.github.xjs.repository.h2.PackageMarker.class,
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager")
public class H2PersistenceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.h2.datasource")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManager(
            @Qualifier("h2DataSource") DataSource h2DataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(h2DataSource)
                .packages(com.github.xjs.entity.h2.PackageMarker.class)
                .properties(Map.of(
                        AvailableSettings.DIALECT,
                        org.hibernate.dialect.H2Dialect.class.getName(),
                        AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED,
                        true))
                .build();
    }

    @Bean
    public PlatformTransactionManager h2TransactionManager(
            @Qualifier("h2EntityManager") LocalContainerEntityManagerFactoryBean h2TransactionManager) {
        return new JpaTransactionManager(h2TransactionManager.getObject());
    }
}
