package com.wuzx.transfer.utils;

import java.sql.SQLException;

public class TransactionManager {


    private ConnectionUtils connectionUtils;


    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }


    /**
     * 提交事务
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }


    /**
     * 回滚事务
     * @throws SQLException
     */
    public void rollBack() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }

}
