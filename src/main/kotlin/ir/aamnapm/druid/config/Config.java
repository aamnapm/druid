package ir.aamnapm.druid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class Config {

    @Bean
    public Connection connection() {
        Properties connectionProperties = new Properties();
        try {
            String url = "jdbc:avatica:remote:url=http://server-ip:port/druid/v2/sql/avatica/";
            return DriverManager.getConnection(url, connectionProperties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
