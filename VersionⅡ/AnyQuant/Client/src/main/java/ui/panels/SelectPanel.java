package ui.panels;




import ui.ControllerFrame;
import ui.myui.DateChooserJButton;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by dell on 2016/3/21.
 */
public class SelectPanel extends JLabel implements ActionListener{
    JButton five,fifteen,thirty,sixty,form,k,line,date,macd,search;
    DateChooserJButton start,end;
    ControllerFrame frame;
String sdate,edate;
    public SelectPanel(ControllerFrame frame){
        this.frame=frame;
        this.setBounds(35,0,965,30);
        this.setOpaque(true);
        this.setBackground(new Color(46,49,56));



        k=new JButton();
        k.setOpaque(false);
        k.setBorder(BorderFactory.createEmptyBorder());
        k.setBackground(Color.darkGray);
        k.setForeground(Color.white);
        k.setBounds(80,0,30,30);
        k.setText("K");
        k.addActionListener(this);
        this.add(k);


        form=new JButton();
        form.setOpaque(false);
        form.setBorder(BorderFactory.createEmptyBorder());
        form.setBackground(Color.darkGray);
        form.setForeground(Color.white);
        form.setBounds(110,0,40,30);
        form.setText("Form");
        form.addActionListener(this);
        this.add(form);

        line=new JButton();
        line.setOpaque(false);
        line.setBorder(BorderFactory.createEmptyBorder());
        line.setBackground(Color.darkGray);
        line.setForeground(Color.white);
        line.setBounds(40,0,40,30);
        line.setText("Line");
        line.addActionListener(this);
        this.add(line);

        five=new JButton();
        five.setOpaque(false);
        five.setBorder(BorderFactory.createEmptyBorder());
        five.setBackground(Color.darkGray);
        five.setForeground(Color.white);
        five.setBounds(150,0,30,30);
        five.setText("5");
        five.addActionListener(this);
        this.add(five);

        fifteen=new JButton();
        fifteen.setOpaque(false);
        fifteen.setBorder(BorderFactory.createEmptyBorder());
        fifteen.setBackground(Color.darkGray);
        fifteen.setForeground(Color.white);
        fifteen.setBounds(180,0,30,30);
        fifteen.setText("15");
        fifteen.addActionListener(this);
        this.add(fifteen);

        thirty=new JButton();
        thirty.setOpaque(false);
        thirty.setBorder(BorderFactory.createEmptyBorder());
        thirty.setBackground(Color.darkGray);
        thirty.setForeground(Color.white);
        thirty.setBounds(210,0,30,30);
        thirty.setText("30");
        thirty.addActionListener(this);
        this.add(thirty);

        sixty=new JButton();
        sixty.setOpaque(false);
        sixty.setBorder(BorderFactory.createEmptyBorder());
        sixty.setBackground(Color.darkGray);
        sixty.setForeground(Color.white);
        sixty.setBounds(240,0,30,30);
        sixty.setText("60");
        sixty.addActionListener(this);
        this.add(sixty);

        start=new DateChooserJButton();
        start.setBounds(670,0,100,30);
        start.setForeground(Color.yellow);
        this.add(start);

        end=new DateChooserJButton();
        end.setBounds(770,0,100,30);
        end.setForeground(Color.yellow);
        this.add(end);

        date=new JButton();
        date.setOpaque(false);
        date.setBorder(BorderFactory.createEmptyBorder());
        date.setBackground(Color.darkGray);
        date.setForeground(Color.GREEN);
        date.setBounds(870,0,30,30);
        date.setText("GO");
        date.addActionListener(this);
        this.add(date);

        search=new JButton();
        search.setOpaque(false);
        search.setBorder(BorderFactory.createEmptyBorder());
        search.setBackground(Color.darkGray);
        search.setForeground(Color.red);
        search.setBounds(900,0,50,30);
        search.setText("Search");
        search.addActionListener(this);
        search.setVisible(false);
        this.add(search);

        macd =new JButton();
        macd.setOpaque(false);
        macd.setBorder(BorderFactory.createEmptyBorder());
        macd.setBackground(Color.darkGray);
        macd.setForeground(Color.green);
        macd.setBounds(0,0,40,30);
        macd.setText("MACD");
        macd.addActionListener(this);
        this.add(macd);




    }
    public void offmacd(){
        macd.setVisible(false);
    }
    public void offsearch(){

        search.setVisible(false);
        five.setVisible(true);
        fifteen.setVisible(true);
        thirty.setVisible(true);
        sixty.setVisible(true);
    }
    public void actionPerformed(ActionEvent events) {
        if (events.getSource() == search) {
           frame.getdeepdata();

        }
        if (events.getSource() == form) {
           search.setVisible(true);
            five.setVisible(false);
            fifteen.setVisible(false);
            thirty.setVisible(false);
            sixty.setVisible(false);
           frame.showForm();

        }
        else if (events.getSource() == k) {
            offsearch();
             frame.showKLine();
        }else if(events.getSource() == five){
            offsearch();
frame.showFive();
        }else if(events.getSource() == fifteen){
            offsearch();
frame.showFifteen();
        }else if(events.getSource() == thirty){
            offsearch();
frame.showThirty();
        }else if(events.getSource() == sixty){
            offsearch();
frame.showSixty();
        }else if(events.getSource() == macd) {
            offsearch();
            frame.showmacd();
        } else if(events.getSource() == line){
            offsearch();
                frame.showLine();
        }else if(events.getSource() == date){
String s=getstartDate();
            String e=getenddate();
            frame.show(s,e);
        }

    }
    public String getstartDate(){

        String s=start.getDate().toLocaleString().substring(0,10);

        if(s.charAt(6)=='-'){
            if(s.charAt(8)==' '){
                s=s.substring(0,5)+'0'+s.substring(5,7)+'0'+s.substring(7,8);
            }else{
                s=s.substring(0,5)+'0'+s.substring(5,7)+s.substring(7,9);
            }
        }else{
            if(s.charAt(9)==' '){
                s=s.substring(0,8)+'0'+s.substring(8,9);
            }
        }
        sdate=s;
        return sdate;
    }
public String getenddate(){
    String e=end.getDate().toLocaleString().substring(0,10);
    if(e.charAt(6)=='-'){
        if(e.charAt(8)==' '){
            e=e.substring(0,5)+'0'+e.substring(5,7)+'0'+e.substring(7,8);
        }else{
            e=e.substring(0,5)+'0'+e.substring(5,7)+e.substring(7,9);
        }
    }else{
        if(e.charAt(9)==' '){
            e=e.substring(0,8)+'0'+e.substring(8,9);
        }
    }
    edate=e;
    return edate;
}

}
