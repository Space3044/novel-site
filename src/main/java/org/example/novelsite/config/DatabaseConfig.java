package org.example.novelsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

@Configuration
public class DatabaseConfig {

    private final DataSource dataSource;

    public DatabaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            org.springframework.core.io.support.EncodedResource resource =
                new org.springframework.core.io.support.EncodedResource(
                    new ClassPathResource("schema.sql"), StandardCharsets.UTF_8);
            ScriptUtils.executeSqlScript(connection, resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
