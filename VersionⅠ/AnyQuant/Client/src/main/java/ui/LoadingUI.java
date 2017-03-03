package ui;

import ui.myui.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by Jiayiwu on 16/3/31.
 */
public class LoadingUI extends JFrame {


    private static final long serialVersionUID = 1L;




    JLabel lblImage =null;
    JPanel panel =null;

    public LoadingUI() {
        panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setWindowStyle ();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setUndecorated(true);
        GetLocation io = new GetLocation();



        this.setBounds(getLocation(scrSize.width, 400), getLocation(scrSize.height, 300), 400, 300);
        io.setX(getLocation(scrSize.width, 400));
        io.setY(getLocation(scrSize.height, 300));

        lblImage = new JLabel(new ImageIcon("ui/loading.gif"));
        lblImage.setBounds(getLocation(scrSize.width, 400), getLocation(scrSize.height, 300), 400, 300);
        lblImage.setVisible(true);
        panel.add(lblImage);
        panel.setBounds(getLocation(scrSize.width, 400), getLocation(scrSize.height, 300), 400, 300);
        panel.setVisible(true);
        this.add(panel);
        this.setLayout(null);










    }



    private int getLocation(int a, int b) {
        return (a - b) / 2;
    }



}
