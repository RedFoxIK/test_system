package ua.test.connection;

public class TransactionManager {
    private final ThreadLocal<ConnectionWrapper> connectionLocal = new ThreadLocal<>();
    private static TransactionManager instance = new TransactionManager();

    private TransactionManager() {}

    public static TransactionManager getInstance() {
        return instance;
    }

    public ConnectionWrapper getConnectionWrapper() {
        ConnectionWrapper connWrap = connectionLocal.get();

        if ( connWrap == null ) {
            connWrap = DataSource.getInstance().getConnection();
        }
        return connWrap;
    }

    public void beginTransaction() {
        ConnectionWrapper connWrap = DataSource.getInstance().getConnection();
        connWrap.setTransactionActive(true);
        connWrap.setAutoCommit(false);
        connectionLocal.set(connWrap);
    }

    public void commit() {
        ConnectionWrapper connWrap = connectionLocal.get();
        connWrap.commit();
        connWrap.setTransactionActive(false);
        connWrap.setAutoCommit(true);
        connectionLocal.get().close();
        connectionLocal.remove();
    }

    public void rollback() {
        connectionLocal.get().rollback();
        connectionLocal.get().close();
        connectionLocal.remove();
    }

}
