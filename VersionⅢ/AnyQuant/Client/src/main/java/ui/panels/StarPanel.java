package ui.panels;

import javax.swing.*;

/**
 * Created by dell on 2016/5/5.
 */
public class StarPanel extends JLabel{
    JLabel la,lb,lc,ld,le;
    public StarPanel(double num){
        this.setOpaque(false);
        la=new JLabel();
        la.setOpaque(false);
        la.setIcon( new ImageIcon("ui/star_dark.png"));
        la.setBounds(0,0,20,20);
        this.add(la);

        lb=new JLabel();
        lb.setOpaque(false);
        lb.setIcon( new ImageIcon("ui/star_dark.png"));
        lb.setBounds(20,0,20,20);
        this.add(lb);

        lc=new JLabel();
        lc.setOpaque(false);
        lc.setIcon( new ImageIcon("ui/star_dark.png"));
        lc.setBounds(40,0,20,20);
        this.add(lc);

        ld=new JLabel();
        ld.setOpaque(false);
        ld.setIcon( new ImageIcon("ui/star_dark.png"));
        ld.setBounds(60,0,20,20);
        this.add(ld);

        le=new JLabel();
        le.setOpaque(false);
        le.setIcon( new ImageIcon("ui/star_dark.png"));
        le.setBounds(80,0,20,20);
        this.add(le);

        int a,b;
       a=(int)num/2;
        b=(int)num%2;
         if(a==0){
           if(b==1)
               la.setIcon( new ImageIcon("ui/star_half.png"));

         }else if(a==1){
             la.setIcon( new ImageIcon("ui/star_pressed.png"));
             if(b==1)
                 lb.setIcon( new ImageIcon("ui/star_half.png"));

         }else if(a==2){
             la.setIcon( new ImageIcon("ui/star_pressed.png"));
             lb.setIcon( new ImageIcon("ui/star_pressed.png"));
             if(b==1)
                 lc.setIcon( new ImageIcon("ui/star_half.png"));
         }else if(a==3){
             la.setIcon( new ImageIcon("ui/star_pressed.png"));
             lb.setIcon( new ImageIcon("ui/star_pressed.png"));
             lc.setIcon( new ImageIcon("ui/star_pressed.png"));
             if(b==1)
                 ld.setIcon( new ImageIcon("ui/star_half.png"));

         }else if(a==4){
             la.setIcon( new ImageIcon("ui/star_pressed.png"));
             lb.setIcon( new ImageIcon("ui/star_pressed.png"));
             lc.setIcon( new ImageIcon("ui/star_pressed.png"));
             ld.setIcon( new ImageIcon("ui/star_pressed.png"));
             if(b==1)
                 le.setIcon( new ImageIcon("ui/star_half.png"));
         }else{
             la.setIcon( new ImageIcon("ui/star_pressed.png"));
             lb.setIcon( new ImageIcon("ui/star_pressed.png"));
             lc.setIcon( new ImageIcon("ui/star_pressed.png"));
             ld.setIcon( new ImageIcon("ui/star_pressed.png"));
             le.setIcon( new ImageIcon("ui/star_pressed.png"));

         }
    }
}
