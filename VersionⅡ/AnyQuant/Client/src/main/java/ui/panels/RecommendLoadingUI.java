package ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jiayiwu on 16/4/2.
 */
public class RecommendLoadingUI extends JPanel {
    private int width =658;
    private int height =494;
    private int vWidth=0;
    private int vHeight=0;
    private static final long serialVersionUID = 1L;
    static JLabel lblImage =new JLabel(new ImageIcon("ui/recommendLoading.gif"));


    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if(this.width<=width) {
            vWidth = (this.getWidth() - this.width) / 2;
        }
        if(this.height<=height){
            vHeight=(this.getHeight()-this.height)/2;
        }
        lblImage.setBounds(vWidth, vHeight, width, height);
        this.add(lblImage);
        lblImage.setVisible(true);
        this.add(lblImage);
        this.setBackground(Color.BLACK);
    }
}
