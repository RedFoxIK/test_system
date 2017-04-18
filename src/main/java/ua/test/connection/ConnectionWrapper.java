package ua.test.connection;

import java.sql.Connection;

public class ConnectionWrapper {
    Connection connection;

    public Connection getConnection() {
        
        return DataSource.getInstance().getConnection();
    }
}
