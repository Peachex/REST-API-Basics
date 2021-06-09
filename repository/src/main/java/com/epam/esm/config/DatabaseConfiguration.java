package com.epam.esm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("com.epam.esm")
public class DatabaseConfiguration {
    private static final String DATABASE_PROPERTY_FILE_PATH = "/property/database.properties";
    private static final String DATABASE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
    private static final String DATABASE_URL = "spring.datasource.url";
    private static final String DATABASE_USERNAME = "spring.datasource.username";
    private static final String DATABASE_Password = "spring.datasource.password";

    @Bean
    public DataSource dataSource() throws IOException {
        Properties properties = new Properties();
        properties.load(DatabaseConfiguration.class.getResourceAsStream(DATABASE_PROPERTY_FILE_PATH));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty(DATABASE_DRIVER_CLASS_NAME));
        dataSource.setUrl(properties.getProperty(DATABASE_URL));
        dataSource.setUsername(properties.getProperty(DATABASE_USERNAME));
        dataSource.setPassword(properties.getProperty(DATABASE_Password));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws IOException {
        return new JdbcTemplate(dataSource());
    }
}
