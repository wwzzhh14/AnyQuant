package NetTestCase;

import junit.framework.TestCase;
import net.LoginNetImpl;
import netservice.LoginNetService;

/**
 * Created by HP on 2016/3/3.
 */
public class TestLoginNetService extends TestCase {

    LoginNetService LoginDemo=new LoginNetImpl();
    public void TestConnect(){
        System.out.println(LoginDemo.connect()
        );
    }

}
