package ui.panels;



import ui.FontFactory;

import javax.swing.*;
import java.awt.*;
/**
 * Created by dell on 2016/3/21.
 */
public class HeadPanel extends JLabel {

    JLabel l_name ,l_code,l_newIndex,l_up,l_rate;


    public HeadPanel() {
        this.setOpaque(true);
        this.setBackground(new Color(46,49,56));
//        this.setFont(new Font("雅黑", Font.PLAIN, 12));
        this.setBounds(1000,0,280,110);

        l_name = new JLabel();
        l_name.setForeground(Color.cyan);
        l_name.setBackground(new Color(0,0,0,0));
        l_name.setFont(FontFactory.getChinese(Font.PLAIN,18));
        l_name.setBounds(40,20,80,30);
        this.add(l_name);

        l_code = new JLabel();
        l_code.setForeground(Color.yellow);
        l_code.setBackground(new Color(0,0,0,0));
        l_code.setFont(FontFactory.getChinese(Font.PLAIN,18));
        l_code.setBounds(130,20,100,30);
        this.add(l_code);

        l_newIndex= new JLabel();
        l_newIndex.setForeground(Color.black);
        l_newIndex.setBackground(new Color(0,0,0,0));
        l_newIndex.setFont(FontFactory.getChinese(Font.PLAIN,20));
        l_newIndex.setBounds(70,50,140,40);
        this.add(l_newIndex);

        l_up = new JLabel();
        l_up.setForeground(Color.white);
        l_up.setBackground(new Color(0,0,0,0));
        l_up.setFont(FontFactory.getChinese(Font.PLAIN,14));
        l_up.setBounds(40,90,100,20);
        this.add(l_up);

        l_rate= new JLabel();
        l_rate.setForeground(Color.white);
        l_rate.setBackground(new Color(0,0,0,0));
        l_rate.setFont(FontFactory.getChinese(Font.PLAIN,14));
        l_rate.setBounds(140,90,100,20);
        this.add(l_rate);

}
    public void  setData(String n,String code,double newi,double u,double r){

        boolean up=true;
        l_name.setText(n);
        if(code==null){
            l_name.setBounds(70,20,80,30);
            l_code.setVisible(false);
        }
        l_code.setText("("+code+")");
        l_newIndex.setText(newi+"");
        if(u<0){
            up=false;
        }
        if(up){
           l_up.setForeground(Color.red);
            l_rate.setForeground(Color.red);
        }else{
            l_up.setForeground(Color.GREEN);
            l_rate.setForeground(Color.green);
        }
        l_up.setText("涨跌："+u);
        l_rate.setText("涨幅："+r+"%");
    }

}
