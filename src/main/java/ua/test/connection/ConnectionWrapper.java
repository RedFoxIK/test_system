package ua.test.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapper implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger(ConnectionWrapper.class);

    private static final String DB_CON_ERROR = "Database connection error";

    private boolean transactionActive;

    Connection conn;

    public ConnectionWrapper(Connection conn) {
        this.conn = conn;
    }

    public boolean isTransactionActive() {
        return transactionActive;
    }

    public void setTransactionActive(boolean transactionActive) {
        this.transactionActive = transactionActive;
    }

    public void setAutoCommit(boolean autoCommit) {
        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR, e);
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR, e);
        }
    }

    public void rollback()  {
        try {
            conn.rollback();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR, e);
        }
    }

    @Override
    public void close()  {
        if ( !this.transactionActive ) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error(DB_CON_ERROR, e);
            }
        }
    }

    public PreparedStatement prepareStatement(String sql, int returnGeneratedKeys) {
        try {
            return this.conn.prepareStatement(sql, returnGeneratedKeys);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR, e);
        }
        return null;
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            return this.conn.prepareStatement(sql);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR, e);
        }
        return null;
    }

    public Statement createStatement() {
        try {
            return this.conn.createStatement();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR, e);
        }
        return null;
    }
}
