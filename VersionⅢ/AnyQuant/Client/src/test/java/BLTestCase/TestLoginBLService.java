package BLTestCase;

import businesslogic.LoginBLImpl;
import businesslogicservice.LoginBLService;
import junit.framework.TestCase;

/**
 * Created by HP on 2016/3/7.
 */
public class TestLoginBLService extends TestCase{

//    public ResultMessage login(String name, String password);
    LoginBLService loginBLService=new LoginBLImpl();
    public void testLogin(){

//        System.out.println(loginBLService.login(null,null).isPassed());
    }
}

