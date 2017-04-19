package ua.test.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionWrapper implements AutoCloseable{
    Connection conn;

    public ConnectionWrapper() {
        this.conn = DataSource.getInstance().getConnection();
    }

    public Connection getConnection() {
        return conn;
    }

    public void setAutoCommit(boolean autoCommit) {
        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback()  {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
