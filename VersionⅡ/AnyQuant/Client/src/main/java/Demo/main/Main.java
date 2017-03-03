package Demo.main;


import Demo.ui.MainFrame;
import stockutil.JsonDataUtil;

import java.util.Random;


/**
 * Created by HP on 2016/3/2.
 */
public class Main {
   static int y =0;
    static int x =5;
     Object [] tem;
    public static void main(String[] args) {


        JsonDataUtil j = JsonDataUtil.instance();
        System.out.println(j.getStock("2016-02-11","2016-03-12","sh600000",null).getData());
        int m=0;
       m =  (x>=3)?(y=x*2+1):((x<=-1)?(y=x*2-1):(y=x*2));
        System.out.println(y);
        System.out.println(m);
//
//        MainFrame frame = new MainFrame();
//        frame.setVisible(true);

    }

    private void test(){
        Random a = new Random();
        tem = new Object[a.nextInt(10)];
    }
}
