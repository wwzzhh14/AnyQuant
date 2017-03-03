package MyDemo.wjy.demo;

import businesslogic.RecommendPythonimpl;
import vo.RecommendVO;

/**
 * Created by Jiayiwu on 16/5/3.
 */
public class Xpath {


     public static void main(String agrs[]){
//         String date = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
//         System.out.println("转换后的时间为：" + date);
         RecommendPythonimpl python =new RecommendPythonimpl();
         RecommendVO vo =python.getRecommendVoByPython("601398");

     }
}
