package MyDemo.wjy.demo;

import businesslogic.RecommendPythonimpl;
import util.JsonDataUtil;
import util.PythonSpider;
import util.StockDataSpider;
import vo.RecommendVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Jiayiwu on 16/5/3.
 */
public class PythonTest {

    public static void main(String agrs[]){
//        try{runpython();}
//        catch (Exception E){
//            E.printStackTrace();
//        }
//        String path = System.getProperty("java.class.path");
//        String match[] = path.split(":");
//        String result ="";
//        for(int i = 0;i<match.length;i++){
//
//
//            if(match[i].contains("AnyQuant/Client")) {
//                result = match[i];
//            }
//        }
//        int start =result.indexOf("Client");
//        result = result.substring(0,start-1);
////        System.out.println();
//        try {
//            runpython(result);
//        }catch (Exception e){
//e.printStackTrace();
//        }
        RecommendVO vo = new RecommendPythonimpl().getRecommendVoByPython("000537");
        System.out.println(vo.getAnalString());
//        PythonSpider.runPython();
//        PythonSpider.initPython();
//        BaseDao dao = new BaseDao();
//        dao.save(new StockInfoPO("sh600000",1,1,1,1,1,1,1,1,1,new Date()));
//        StockDataSpider.refresh();
//        JsonDataUtil j = JsonDataUtil.instance();
//        System.out.println(j.getNowTimeStockResult("sh601998").getData());
    }


    static void runpython(String path) throws IOException, InterruptedException {
        String mypath = path+"/mydata/Test.py";
        mypath= "python "+ mypath;
        String[] args = new String[] {"python /Users/Jiayiwu/Documents/南京大学/AnyQuant/AnyQuant/Version3/AnyQuant/mydata/Test.py   ","a","b", "c","d" };
        Process process = Runtime.getRuntime().exec("python /Users/Jiayiwu/Documents/南京大学/AnyQuant/AnyQuant/Version3/AnyQuant/mydata/Test.py   a   b ");
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
