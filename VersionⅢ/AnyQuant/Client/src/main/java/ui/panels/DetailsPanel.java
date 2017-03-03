package ui.panels;



import ui.FontFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2016/3/21.
 */
public class DetailsPanel  extends JLabel {

     double yclose=0.0,newindex=0,open=0,high=0,low=0,uord=0,udrate=0,all=0,allm=0;
    JLabel l_date,l_time,l_yclose,l_new,l_o,l_h,l_l,l_v,l_v2,l_a,l_a2;
    public DetailsPanel(){

        this.setOpaque(true);
        this.setBackground(new Color(46,49,56));
        this.setFont(FontFactory.getChinese(Font.PLAIN,12));
        this.setBounds(1000,110,280,230);

        JLabel word_d = new JLabel("日期", JLabel.CENTER);
        word_d.setForeground(Color.white);
        word_d.setBackground(new Color(0, 0, 0, 0));
        word_d.setBounds(5, 5, 100, 20);
        this.add(word_d);

        l_date = new JLabel();
        l_date.setForeground(Color.cyan);
        l_date.setBackground(new Color(0, 0, 0, 0));
        l_date.setBounds(180, 5, 100, 20);
        this.add(l_date);

        JLabel word_t = new JLabel("时间", JLabel.CENTER);
        word_t.setForeground(Color.white);
        word_t.setBackground(new Color(0, 0, 0, 0));
        word_t.setBounds(5, 25, 100, 20);
        this.add(word_t);

        l_time = new JLabel();
        l_time.setForeground(Color.cyan);
        l_time.setBackground(new Color(0, 0, 0, 0));
        l_time.setBounds(180, 25, 100, 20);
        this.add(l_time);

        JLabel word_yclose = new JLabel("昨日收盘", JLabel.CENTER);
        word_yclose.setForeground(Color.white);
        word_yclose.setBackground(new Color(0, 0, 0, 0));
        word_yclose.setBounds(5, 45, 100, 20);
        this.add(word_yclose);

        l_yclose = new JLabel();
        l_yclose.setForeground(Color.white);
        l_yclose.setBackground(new Color(0, 0, 0, 0));
        l_yclose.setBounds(180, 45, 100, 20);
        l_yclose.setText(yclose+"");
        this.add(l_yclose);


        JLabel word_new= new JLabel("最新指数", JLabel.CENTER);
        word_new.setForeground(Color.white);
        word_new.setBackground(new Color(0, 0, 0, 0));
        word_new.setBounds(5, 65, 100, 20);
        this.add(word_new);

    l_new = new JLabel();
        l_new.setForeground(Color.white);
        l_new.setBackground(new Color(0, 0, 0, 0));
        l_new.setBounds(180, 65, 100, 20);
        l_new.setText(newindex+"");
        this.add(l_new);

        JLabel word_open = new JLabel("开盘指数", JLabel.CENTER);
        word_open.setForeground(Color.white);
        word_open.setBackground(new Color(0, 0, 0, 0));
        word_open.setBounds(5, 85, 100, 20);
        this.add(word_open);

         l_o = new JLabel();
        l_o.setForeground(Color.white);
        l_o.setBackground(new Color(0, 0, 0, 0));
        l_o.setBounds(180, 85, 100, 20);
        l_o.setText(open+"");
        this.add(l_o);


        JLabel word_high = new JLabel("最高指数", JLabel.CENTER);
        word_high.setForeground(Color.white);
        word_high.setBackground(new Color(0, 0, 0, 0));
        word_high.setBounds(5, 105, 100, 20);
        this.add(word_high);

      l_h = new JLabel();
        l_h.setForeground(Color.white);
        l_h.setBackground(new Color(0, 0, 0, 0));
        l_h.setBounds(180, 105, 100, 20);
        l_h.setText(high+"");
        this.add(l_h);


        JLabel word_low = new JLabel("最低指数", JLabel.CENTER);
        word_low.setForeground(Color.white);
        word_low.setBackground(new Color(0, 0, 0, 0));
        word_low.setBounds(5, 125, 100, 20);
        this.add(word_low);


       l_l = new JLabel();
        l_l.setForeground(Color.white);
        l_l.setBackground(new Color(0, 0, 0, 0));
        l_l.setBounds(180, 125, 100, 20);
        l_l.setText(low+"");
        this.add(l_l);

        JLabel word_volume = new JLabel("指数涨跌", JLabel.CENTER);
        word_volume.setForeground(Color.white);
        word_volume.setBackground(new Color(0, 0, 0, 0));
        word_volume.setBounds(5, 145, 100, 20);
        this.add(word_volume);

        l_v = new JLabel();
        l_v.setForeground(Color.white);
        l_v.setBackground(new Color(0, 0, 0, 0));
        l_v.setBounds(180, 145, 100, 20);
        l_v.setText(uord+"");
        this.add(l_v);

        JLabel word_volume2 = new JLabel("涨跌幅", JLabel.CENTER);
        word_volume2.setForeground(Color.white);
        word_volume2.setBackground(new Color(0, 0, 0, 0));
        word_volume2.setBounds(5, 165, 100, 20);
        this.add(word_volume2);

         l_v2 = new JLabel();
        l_v2.setForeground(Color.white);
        l_v2.setBackground(new Color(0, 0, 0, 0));
        l_v2.setBounds(180, 165, 100, 20);
        l_v2.setText(udrate+"");
        this.add(l_v2);

        JLabel word_all = new JLabel("总成交量", JLabel.CENTER);
        word_all.setForeground(Color.white);
        word_all.setBackground(new Color(0, 0, 0, 0));
        word_all.setBounds(5, 185, 100, 20);
        this.add(word_all);

         l_a = new JLabel();
        l_a.setForeground(Color.yellow);
        l_a.setBackground(new Color(0, 0, 0, 0));
        l_a.setBounds(180, 185, 100, 20);
        l_a.setText(all+"");
        this.add(l_a);

        JLabel word_all2 = new JLabel("总成交额", JLabel.CENTER);
        word_all2.setForeground(Color.white);
        word_all2.setBackground(new Color(0, 0, 0, 0));
        word_all2.setBounds(5, 205, 100, 20);
        this.add(word_all2);
        this.setVisible(true);

         l_a2 = new JLabel();
        l_a2.setForeground(Color.yellow);
        l_a2.setBackground(new Color(0, 0, 0, 0));
        l_a2.setBounds(180, 205, 100, 20);
        l_a2.setText(allm+"");
        this.add(l_a2);


}

    public void setDate(String date,String time,double yc,double newind,double o,double h, double l,double ud, double rate,double a,double m){
        boolean up=true;
//        l_date,l_time,l_yclose,l_new,l_o,l_h,l_l,l_v,l_v2,l_a,l_a2;
        if(ud<0){
            up=false;
        }
        if(up){
            l_new.setForeground(Color.red);
            l_o.setForeground(Color.red);
            l_h.setForeground(Color.red);
            l_l.setForeground(Color.red);
            l_v.setForeground(Color.red);
            l_v2.setForeground(Color.red);
        }else{
            l_new.setForeground(Color.green);
            l_o.setForeground(Color.green);
            l_h.setForeground(Color.green);
            l_l.setForeground(Color.green);
            l_v.setForeground(Color.green);
            l_v2.setForeground(Color.green);
        }

        l_date.setText(date+"");
        l_time.setText(time+"");
        l_yclose.setText(yc+"");
        l_new.setText(newind+"");
        l_o.setText(o+"");
        l_h.setText(h+"");
        l_l.setText(l+"");
        l_v.setText(ud+"");
        l_v2.setText(rate+"");
        l_a.setText(a+"");
        l_a2.setText(m+"");


    }
}
