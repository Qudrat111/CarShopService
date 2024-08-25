package util;

import org.springframework.beans.factory.annotation.Value;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    @Value("${spring.datasource.jdbc.url}")
    private String url;
    @Value("${spring.datasource.jdbc.user}")
    private String user;
    @Value("${spring.datasource.jdbc.password}")
    private String password;


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
