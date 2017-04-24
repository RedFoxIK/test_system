package ua.test.connection;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DataSource {
    private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);

    private final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/testing_system?useSSL=false";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "1111";

    private static final String DB_CON_ERROR = "Database connection error";

    private static final DataSource instance = new DataSource();
    private BoneCP connPool;


    public static DataSource getInstance() {
        return instance;
    }

    public ConnectionWrapper getConnection()  {
        ConnectionWrapper connWrap = null;

        try {
            connWrap = new ConnectionWrapper(this.connPool.getConnection());
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
        }
        return connWrap;
    }

    private DataSource() {
        initDataSource();
    }

    private void initDataSource() {
        BoneCPConfig poolConfig = new BoneCPConfig();

        try {
            Class.forName(DB_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        poolConfig.setJdbcUrl(DB_URL);
        poolConfig.setUsername(DB_USERNAME);
        poolConfig.setPassword(DB_PASSWORD);

        poolConfig.setMinConnectionsPerPartition(2);
        poolConfig.setMaxConnectionsPerPartition(20);
        poolConfig.setPartitionCount(1);
        try {
            connPool = new BoneCP(poolConfig);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
