package MyDemo.wzh.demo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HP on 2016/3/14.
 */
public class DemoFrame {

    public static void main(String[] args){
        JFrame frame=new JFrame();
        frame.setBounds(100,100,800,600);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        double[] d1={100,101,105,90,120};
        double[] d2={2000,2021,1960,2050,1920};
        String[] dates={"2016-01-01","2016-01-02","2016-01-03","2016-01-04","2016-01-05"};
        Demo02 panel=new Demo02(0,0,frame.getWidth(),frame.getHeight(),Color.black,d1,d2,Color.red,Color.green,dates);
//        panel.drawLine(d, Color.RED);



//        Chart c=Demo01.getChart();
//        XChartPanel panel=new XChartPanel(c);
//        panel.setBounds(10,10,600,400);
//        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);

    }


}
