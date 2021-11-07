package com.wuzx.transfer.factory;

import com.wuzx.transfer.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@Component("proxyFactory")
public class ProxyFactory {

    @Autowired
    private TransactionManager transactionManager;

    public Object getJDKProxy(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                try {
                    // 开启事务
                    transactionManager.beginTransaction();

                    // 执行原方法
                    final Object result = method.invoke(object, args);

                    // 提交事务
                    transactionManager.commit();
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    // 回滚事务
                    transactionManager.rollBack();
                    throw e;
                }
            }
        });
    }


    public Object getCglibProxy(Object object) {
        return Enhancer.create(object.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                try {
                    // 开启事务
                    transactionManager.beginTransaction();

                    // 执行原方法
                    final Object result = method.invoke(object, objects);

                    // 提交事务
                    transactionManager.commit();
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    // 回滚事务
                    transactionManager.rollBack();
                    throw e;
                }
            }
        });
    }
}
