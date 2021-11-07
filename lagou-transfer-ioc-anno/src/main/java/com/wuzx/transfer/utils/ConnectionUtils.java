package com.wuzx.transfer.utils;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionUtils {

    @Autowired
    private DataSource dataSource;

    // 存储当前线程的连接
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 从当前线程获取连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getCurrentThreadConn() throws SQLException {
        /**
         * 判断当前线程中是否已经绑定连接，如果没有绑定，需要从连接池获取一个连接绑定到当前线程
         */
        Connection connection = threadLocal.get();
        if (null == connection) {
            // 从连接池拿连接并绑定到线程
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }

        return connection;
    }
}
