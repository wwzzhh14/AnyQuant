package businesslogicservice;

import config.ResultMessage;

/**
 * Created by HP on 2016/3/2.
 */
public interface LoginBLService {
//    登录校验接口
    public ResultMessage login(String name,String password);
}
