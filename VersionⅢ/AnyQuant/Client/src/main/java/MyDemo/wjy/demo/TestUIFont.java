package MyDemo.wjy.demo;

import util.JsonDataUtil;

/**
 * Created by Jiayiwu on 16/4/10.
 */
public class TestUIFont {
    public static void main (String args[]) {
//        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[] fontNames = e.getAvailableFontFamilyNames();
//        for(String s :fontNames)
//            System.out.println(s);
//    }

        System.out.println(JsonDataUtil.instance().getStock("2014-01-01","2016-05-23","sh600000",null).getData());

    }
}
