package com.wuzx.transfer.service.impl;


import com.wuzx.transfer.dao.AccountDao;
import com.wuzx.transfer.pojo.Account;
import com.wuzx.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 应癫
 */
@Service("transferService")
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountDao accountDao;


    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);

        accountDao.updateAccountByCardNo(to);

        accountDao.updateAccountByCardNo(from);
    }
}
