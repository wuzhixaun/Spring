package com.wuzx.transfer.utils;



import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    private static ConnectionUtils connectionUtils = new ConnectionUtils();
    private ConnectionUtils() {

    }

    public static ConnectionUtils getInstance() {
        return connectionUtils;
    }


    /**
     * 从当前线程获取连接
     * @return
     * @throws SQLException
     */
    public Connection getCurrentThreadConn() throws SQLException {
        Connection connection = threadLocal.get();

        if (null == connection) {
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }

        return connection;
    }
}
