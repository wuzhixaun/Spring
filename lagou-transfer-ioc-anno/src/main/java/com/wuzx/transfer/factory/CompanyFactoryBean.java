package com.wuzx.transfer.factory;

import com.wuzx.transfer.pojo.Company;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CompanyFactoryBean implements FactoryBean<Company> {


    private String companyInfo = "拉勾,中关村,500";

    @Override
    public Company getObject() throws Exception {

        // 模拟创建复杂对象Company
        Company company = new Company();
        String[] strings = companyInfo.split(",");
        company.setName(strings[0]);
        company.setAddress(strings[1]);
        company.setScale(Integer.parseInt(strings[2]));
        return company;
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
