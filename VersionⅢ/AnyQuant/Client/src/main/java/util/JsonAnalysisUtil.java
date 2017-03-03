package util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by HP on 2016/3/4.
 */
public class JsonAnalysisUtil {

    public static <T> T json2Bean(String jsonStr, Class<T> objClass){
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
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
