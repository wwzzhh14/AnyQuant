package businesslogic;

import businesslogicservice.LoginBLService;
import config.ResultMessage;
import net.LoginNetImpl;
import netservice.LoginNetService;

/**
 * Created by HP on 2016/3/5.
 */
public class LoginBLImpl implements LoginBLService {

    private LoginNetService loginNet=new LoginNetImpl();
    private ResultMessage resultMessage;

    public ResultMessage login(String name, String password) {
        if(loginNet.connect()){
            resultMessage=new ResultMessage(" ",true);
        }else {
            resultMessage=new ResultMessage("网络连接失败",false);
        }
        return resultMessage;
    }
}
