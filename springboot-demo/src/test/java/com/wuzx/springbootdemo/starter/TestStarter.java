package com.wuzx.springbootdemo.starter;

import com.wuzx.pojo.SimpleBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName TestStarter.java
 * @Description TODO
 * @createTime 2021年11月22日 10:34:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStarter {

    @Autowired
    private SimpleBean simpleBean;

    @Test
    public void testStater() {
        System.out.println(simpleBean);
    }
}
