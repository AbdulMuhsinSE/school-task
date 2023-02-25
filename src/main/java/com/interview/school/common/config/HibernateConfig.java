package com.interview.school.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * HibernateConfig.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.interview.school")
public class HibernateConfig {
    @Bean(name = "schoolDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public UpperSnakeCasePhysicalNamingStrategy upperSnakeCasePhysicalNamingStrategy() {
        return new UpperSnakeCasePhysicalNamingStrategy();
    }
}
