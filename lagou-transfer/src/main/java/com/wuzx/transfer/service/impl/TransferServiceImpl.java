package com.wuzx.transfer.service.impl;


import com.wuzx.transfer.dao.AccountDao;
import com.wuzx.transfer.dao.impl.JdbcAccountDaoImpl;
import com.wuzx.transfer.factory.BeanFactory;
import com.wuzx.transfer.pojo.Account;
import com.wuzx.transfer.service.TransferService;
import com.wuzx.transfer.utils.TransactionManager;

/**
 * @author 应癫
 */
public class TransferServiceImpl implements TransferService {

    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        try {
            // 开启事务
            TransactionManager.getInstance().beginTransaction();

            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(to);

            int c = 1 / 0;
            accountDao.updateAccountByCardNo(from);

            // 提交事务
            TransactionManager.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常");
            // 回滚事务
            TransactionManager.getInstance().rollBack();
            throw e;
        }


    }
}
