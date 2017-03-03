//package DemoJaso;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import stockutil.JsonDataUtil;
//import config.ExchangePlace;
//
//public class myTest {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		 JsonDataUtil tem = JsonDataUtil.instance();
//
//	       String tem1 = tem.getAllStocks(2014, ExchangePlace.sh).getData();
//	       tem1=tem1.substring(tem1.indexOf('['), tem1.length()-1);
//	       Object [] tem2 = getDTOArray(tem1,DataVo.class);
//	        System.out.println(((DataVo)tem2[0]).link);
//	}
//
//
//	 public static <T> T json2Bean(String jsonStr, Class<T> objClass){
//	        return (T)JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
//
//	    }
//
//	public static Object[] getDTOArray(String jsonString, Class clazz){
//
//        JSONArray array = JSONArray.fromObject(jsonString);
//       Object[] obj = new Object[array.size()];
//            for(int i = 0; i < array.size(); i++){
//                 JSONObject jsonObject = array.getJSONObject(i);
//                obj[i] = JSONObject.toBean(jsonObject, clazz);
//
//            }
//            return obj;
//  }
//}
