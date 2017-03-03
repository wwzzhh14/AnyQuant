package util;

import javax.swing.*;
import java.io.*;

/**
 * Created by Jiayiwu on 16/5/4.
 */
public class PythonSpider {

    private PythonSpider(){

    }

    public static void initPython() {


        if (isJudge()) {
            try {
                htmlCrawler.getHtml();
                runPython();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "文件配置文件错误,请检查路径或重新导入程序", "系统信息", JOptionPane.ERROR_MESSAGE);
            }


        }else
            return;
    }


    private static boolean isJudge(){
        File file = new File("mydata/date.lg");
        if(!file.exists())
            return true;
       try {
           BufferedReader br = new BufferedReader(new FileReader("mydata/date.lg"));
           String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
           String nowDay =br.readLine();
           if(!(nowDay.equals(date))){
               return true;
           }
       }catch (Exception e){
           JOptionPane.showMessageDialog(null,
                   "文件配置文件错误,请检查路径或重新导入程序", "系统信息", JOptionPane.ERROR_MESSAGE);
       }
        return false;
    }


    public static void runPython(){
        String path = System.getProperty("java.class.path");
        String match[] = path.split(":");
        String result ="";
        for(int i = 0;i<match.length;i++){


            if(match[i].contains("AnyQuant/Client")) {
                result = match[i];
            }
        }
        int start =result.indexOf("Client");
        result = result.substring(0,start-1);

        String mypath = result+"/mydata/webSpider.py";
        mypath = "python "+mypath;
        try {
            Process process = Runtime.getRuntime().exec(mypath);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "未能在找到合适的Python运行环境,请检查环境中是否配置了Python以及导入扩展包lxml,推荐数据无法更新", "系统信息", JOptionPane.ERROR_MESSAGE);
        }
    }
}
