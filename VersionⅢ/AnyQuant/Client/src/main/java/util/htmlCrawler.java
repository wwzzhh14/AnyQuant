package util;



import org.w3c.tidy.Tidy;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jiayiwu on 16/5/3.
 */
public class htmlCrawler {

    private htmlCrawler(){

    }

    public static void getHtml() throws IOException{

        BufferedReader brF = new BufferedReader(new FileReader("mydata/stockName.txt"));
        String codeNum = "";
        while ((codeNum = brF.readLine())!=null&&(!codeNum.equals(""))) {

                    codeNum=codeNum.substring(2,codeNum.length());
                String website = "http://stockpage.10jqka.com.cn/";
                website += codeNum;


                String path = "mydata/meta/";
                path = path + codeNum + ".data";
                StringBuffer result = new StringBuffer();
                InputStream in = null;
                try {
                    URL url = new URL(website);
                    URLConnection con = url.openConnection();
                    con.connect();
                    in = con.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String line = null;
                    while ((line = br.readLine()) != null) {

                        result.append(new String(line.getBytes(), "utf-8") + "\n");

                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
                writer.write(result.toString());

        }
    }


}
