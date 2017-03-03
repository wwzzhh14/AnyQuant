package ui.panels;

import vo.RecommendOrange;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/5/5.
 */
public class test {
    public static void main(String []args){
        JFrame frame=new JFrame();
        frame.setBounds(200,100,1280,720);

        RecommendPanel orangeRecommend=new RecommendPanel("sh601166");
        orangeRecommend.setBounds(0,0,1280,720);
        frame.add(orangeRecommend);
        frame.setVisible(true);
    }
}
