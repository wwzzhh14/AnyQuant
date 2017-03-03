package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jiayiwu on 16/3/31.
 */
public class LoadingUI extends JFrame {


    private static final long serialVersionUID = 1L;

    private int width = 400;
    private int height =300;

    static  JPanel lblImagePanel;
    static JLabel lblImage;

    public LoadingUI(int num) {
        String s ="ui/loading"+num+".gif";
        lblImagePanel=new JPanel();
        lblImagePanel.setBounds(0, 0, width, height);
        lblImage  =new JLabel(new ImageIcon(s));
        lblImage.setBounds(0, 0, width, height);
        lblImage.setVisible(true);
        lblImagePanel.setVisible(true);
        lblImagePanel.add(lblImage);
        this.add(lblImagePanel);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setWindowStyle ();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setUndecorated(true);
        GetLocation io = new GetLocation();



        this.setBounds(getLocation(scrSize.width, width), getLocation(scrSize.height, height), width, height);
        io.setX(getLocation(scrSize.width, width));
        io.setY(getLocation(scrSize.height, height));




        this.setLayout(null);









    }



    private int getLocation(int a, int b) {
        return (a - b) / 2;
    }



}
