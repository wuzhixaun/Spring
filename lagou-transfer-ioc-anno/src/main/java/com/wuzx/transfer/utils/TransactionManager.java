package com.wuzx.transfer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TransactionManager {


    @Autowired
    private ConnectionUtils connectionUtils;

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
