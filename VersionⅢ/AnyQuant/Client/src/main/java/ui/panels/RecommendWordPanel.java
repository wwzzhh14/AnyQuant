package ui.panels;

import ui.MainFrame;
import ui.myui.EmptyTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by elva on 2016/4/13.
 */
public class RecommendWordPanel extends JLabel{
    public static String name;
    JFrame frame;
    EmptyTextField textField_highbig = new EmptyTextField(400,560,500,200);
    EmptyTextField textField_highsmall = new EmptyTextField(400,560,500,200);
    EmptyTextField textField_lowbig = new EmptyTextField(400,560,500,200);
    EmptyTextField textField_lowsmall = new EmptyTextField(400,560,500,200);


    public RecommendWordPanel(final MainFrame frame) {
        this.setLayout(null);
        this.setBounds(336,480,922,200);

        this.frame = frame;
        ImageIcon img = new ImageIcon("ui/recommend_word.png");// 这是背景图片
        JLabel imgLabel = new JLabel(img);// 将背景图放在标签里

    }



    public void init(int x,int y,boolean stoped){

        this.setBounds(x, y, 922, 200);
        frame.add(this);
    }
}
