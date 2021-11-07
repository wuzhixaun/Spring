import com.wuzx.transfer.SpringConfig;
import com.wuzx.transfer.dao.AccountDao;
import com.wuzx.transfer.pojo.Company;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {


    @Test
    public void test() {
        // 通过读取 配置文件启动
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        final AccountDao accountDao = (AccountDao) context.getBean("accountDao");

        System.out.println(accountDao);

    }


    @Test
    public void testLazy() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        final AccountDao accountDao = (AccountDao) context.getBean("accountDao");
        System.out.println(accountDao);
    }


    @Test
    public void testFactoryBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        final Object Object = context.getBean("&companyFactoryBean");
        System.out.println(Object);
    }
}
