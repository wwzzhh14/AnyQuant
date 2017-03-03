package Demo.wjy.demo;

import java.awt.*;

/**
 * Created by Jiayiwu on 16/4/10.
 */
public class TestUIFont {
    public static void main (String args[]){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = e.getAvailableFontFamilyNames();
        for(String s :fontNames)
            System.out.println(s);
    }
}
