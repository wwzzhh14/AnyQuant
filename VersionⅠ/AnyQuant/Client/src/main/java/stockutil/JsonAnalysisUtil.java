package stockutil;

import config.State;
import config.StringMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by HP on 2016/3/4.
 */
public class JsonAnalysisUtil {

    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
//		 JSONObject jObject_1=JSONObject.fromObject(jsonStr);
//		 if (jObject_1.getString("status").equals("ok")){
//			 JSONObject jObject_2=jObject_1.getJSONObject("data");
//			 JSONArray JArray=jObject_2.getJSONArray("trading_info");
//			 String s=JArray.toString();
//			 System.out.println(s);
//			 return null;
//
//		 }else {
//			 return null;
//		 }
    }
    //
    public static Object[] getDTOArray(String jsonString, Class clazz){

        JSONArray array = JSONArray.fromObject(jsonString);
        Object[] obj = new Object[array.size()];
        for(int i = 0; i < array.size(); i++){
            JSONObject jsonObject = array.getJSONObject(i);
            obj[i] = JSONObject.toBean(jsonObject, clazz);

        }
        return obj;
    }

}
