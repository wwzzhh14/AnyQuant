package net;

import config.State;
import config.StringMessage;
import net.sf.json.JSONObject;
import netservice.LoginNetService;
import util.JsonDataUtil;

/**
 * Created by HP on 2016/3/3.
 */
public class LoginNetImpl implements LoginNetService {

    JsonDataUtil jsonDataUtil=JsonDataUtil.instance();

    public boolean connect() {
        StringMessage stringMessage=jsonDataUtil.getConnectResult();
        if(stringMessage.getResult()== State.OK){
            String jsonString=stringMessage.getData();
            String state=JSONObject.fromObject(jsonString).getString("status");
            if(state.equals("ok")){
               return true;
            }
        }
        return false;
    }
}
