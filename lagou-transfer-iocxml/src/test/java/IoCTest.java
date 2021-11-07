
import com.wuzx.transfer.dao.impl.JdbcAccountDaoImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author 应癫
 */
public class IoCTest {


    @Test
    public void testIoC() throws Exception {

        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        // 不推荐使用
        //ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("文件系统的绝对路径");


        // 第一次getBean该对象
//        Object accountPojo = applicationContext.getBean("accountPojo");



        Object connectionUtils = applicationContext.getBean("connectionUtils");
        JdbcAccountDaoImpl accountDao = (JdbcAccountDaoImpl)applicationContext.getBean("accountDao");
        System.out.println  (connectionUtils);

        applicationContext.close();
        }


    @Test
    public void test() {
        System.out.println("qqq");
    }

}
