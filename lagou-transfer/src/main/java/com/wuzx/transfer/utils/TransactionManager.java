package com.wuzx.transfer.utils;

import java.sql.SQLException;

public class TransactionManager {


    private TransactionManager() {

    }

    private static TransactionManager transactionManager = new TransactionManager();

    public static TransactionManager getInstance() {
        return transactionManager;
    }


    /**
     * 开启事务
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().setAutoCommit(false);
    }


    /**
     * 提交事务
     * @throws SQLException
     */
    public void commit() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().commit();
    }


    /**
     * 回滚事务
     * @throws SQLException
     */
    public void rollBack() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().rollback();
    }

}
