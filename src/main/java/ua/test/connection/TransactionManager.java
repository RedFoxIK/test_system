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
        ConnectionWrapper connWprap = DataSource.getInstance().getConnection();
        connWprap.setTransactionActive(true);
        connWprap.setAutoCommit(false);
        connectionLocal.set(connWprap);
    }

    public void commit() {
        ConnectionWrapper connWrap = connectionLocal.get();
        connWrap.commit();
        connWrap.setTransactionActive(false);
        connWrap.setAutoCommit(false);
        close();
    }

    public void rollback() {
        connectionLocal.get().rollback();
    }

    public void close()  {
        connectionLocal.get().close();
        connectionLocal.remove();
    }

}
