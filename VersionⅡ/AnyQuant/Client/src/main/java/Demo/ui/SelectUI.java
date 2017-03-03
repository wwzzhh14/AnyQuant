package Demo.ui;


import Demo.ui.myui.EmptyTextField;
import Demo.ui.myui.MyButton;
import Demo.ui.myui.MyCheckBox;
import vo.SelectValueBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

/**
 * Created by dell on 2016/3/11.
 */
public class SelectUI extends JFrame {

//            * open:开盘价、
//            * high:最高价、
//            * low:最低价、
//            * close:收盘价、

//            * volume:成交量、
//
boolean isDraging;
    int xx, yy, X0, Y0, X, Y;
    JPanel SelectPanel ;
    MyButton Search;
    MyButton Cancle;
    EmptyTextField textField_open_s;
    EmptyTextField textField_open_e, textField_high_s, textField_high_e, textField_low_s,textField_low_e,textField_close_s,textField_close_e;
    EmptyTextField textField_volume_s, textField_volume_e;

    MyButton bopen,bhigh,blow,bclose,bvolume;
    MyCheckBox box_open,box_high,box_low,box_close,box_volume;
    ArrayList data = new ArrayList<SelectValueBean>();
    SelectValueBean open = new SelectValueBean();
    SelectValueBean low = new SelectValueBean();
    SelectValueBean close = new SelectValueBean();
    SelectValueBean high = new SelectValueBean();
    SelectValueBean volume = new SelectValueBean();

 int type=0;
    public SelectUI(int t){
        type=t;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setWindowStyle ();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setUndecorated(true);
        GetLocation io = new GetLocation();
        this.setBounds(getLocation(scrSize.width, 600), getLocation(scrSize.height,350 ), 600, 350);
        io.setX(getLocation(scrSize.width, 600));
        io.setY(getLocation(scrSize.height, 350));
        this.setLayout(null);
        ImageIcon img = new ImageIcon("ui/DeepSearch350.png");// 这是背景图片
        JLabel imgLabel = new JLabel(img);// 将背景图放在标签里。

        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的
// LayeredPane面板里。
        imgLabel.setBounds(0, 0, this.getWidth(), this.getHeight());// 设置背景标签的位置
//	imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// 设置背景标签的位置
        Container cp = this.getContentPane();
        cp.setLayout(null); // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
        ((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了


        //the codes that make the frame mouse-drag-able
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                requestFocus();
                isDraging = true;
                xx = e.getX();
                yy = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                isDraging = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDraging) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    setLocation(left + e.getX() - xx, top + e.getY() - yy);
                    X = left + e.getX() - xx;
                    Y = top + e.getY() - yy;
                    GetLocation io = new GetLocation();

                    io.setY(Y);
                }
            }
        });

        new HyalineValue().start(); // 透明渐变启动界面
        init();

    }

    /**
     * 透明度渐变启动界面
     *
     * @author cylong
     * @version 2014年12月12日 上午3:25:27
     */
    protected class HyalineValue extends Thread {

        float hyalineValue = 0f;

        public void run() {
            while (true) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hyalineValue += 0.05f;
                if (hyalineValue > 1) {
                    hyalineValue = 1;
                }
                setOpacity(hyalineValue);
                if (hyalineValue == 1) {
                    break;
                }

            }
        }
    }

    //return a location to make sure the frame initialize in the middle of users computer
    private int getLocation(int a, int b) {
        return (a - b) / 2;
    }
//    JPanel SelectPanel ;
//    JButton Search;
//    JButton Cancle;
//    MyTextField textField_open_s, textField_open_e, textField_high_s, textField_high_e, textField_low_s,textField_low_e,textField_close_s,getTextField_close_e;
//    MyTextField  textField_volume_s, textField_volume_e;
//    MyCheckBox  box_open,box_high,box_low,box_close,box_volume;
        private void init(){
            SelectPanel=new JPanel();
            SelectPanel.setBounds(0,0,600,350);
            SelectPanel.setVisible(true);
            SelectPanel.setOpaque(false);
            SelectPanel.setLayout(null);
            this.add(SelectPanel);

            Search = new MyButton(133, 292, 144, 36);
            Search.setIcon(new ImageIcon("ui/Yellow Mini Button.png"));
            Search.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent arg0) {

                }

                public void mouseClicked(MouseEvent arg0) {

                    if (box_open.isSelected()){
                        open.setStart(Double.parseDouble(textField_open_s.getText().toString()));
                        open.setEnd(Double.parseDouble(textField_open_e.getText().toString()));
                    } else{
                        open = null;
                    }

                    if (box_high.isSelected()){
                        high.setStart(Double.parseDouble(textField_high_s.getText().toString()));
                        high.setEnd(Double.parseDouble(textField_high_e.getText().toString()));
                    } else{
                        high = null;
                    }

                    if (box_low.isSelected()){
                        low.setStart(Double.parseDouble(textField_low_s.getText().toString()));
                        low.setEnd(Double.parseDouble(textField_low_e.getText().toString()));
                    } else{
                        low = null;
                    }

                    if (box_close.isSelected()){
                        close.setStart(Double.parseDouble(textField_close_s.getText().toString()));
                        close.setEnd(Double.parseDouble(textField_close_e.getText().toString()));
                    } else{
                        close = null;
                    }

                    if (box_volume.isSelected()){
                        volume.setStart(Double.parseDouble(textField_volume_s.getText().toString()));
                        volume.setEnd(Double.parseDouble(textField_volume_e.getText().toString()));
                    } else{
                        volume = null;
                    }

                    data.add(open);
                    data.add(high);
                    data.add(low);
                    data.add(close);
                    data.add(volume);

                    if(type==0) {

                        StockDetailPanel.button_deep.doClick();
                    }else{
                        MarketInfoPanel.button_deep.doClick();
                    }
                    close();

                }

                public void mouseExited(MouseEvent arg0) {



                }
            });
            SelectPanel.add(Search);

            Cancle = new MyButton(331,292, 144, 36);
            Cancle.setIcon(new ImageIcon("ui/Dark Mini Button.png"));

            Cancle.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent arg0) {

                }

                public void mouseClicked(MouseEvent arg0) {
                    close();
                }

                public void mouseExited(MouseEvent arg0) {



                }
            });
            SelectPanel.add(Cancle);


//            JLabel word_open = new JLabel("开盘价", JLabel.CENTER);
//            word_open.setForeground(Color.DARK_GRAY);
//            word_open.setBackground(new Color(0, 0, 0, 0));
//            word_open.setBounds(100, 40, 100, 25);
//            SelectPanel.add(word_open);
//
//            JLabel word_high= new JLabel("最高价", JLabel.CENTER);
//            word_high.setForeground(Color.DARK_GRAY);
//            word_high.setBackground(new Color(0, 0, 0, 0));
//            word_high.setBounds(100, 90, 100, 25);
//            SelectPanel.add(word_high);
//
//            JLabel word_close = new JLabel("收盘价", JLabel.CENTER);
//            word_close.setForeground(Color.DARK_GRAY);
//            word_close.setBackground(new Color(0, 0, 0, 0));
//            word_close.setBounds(100, 140, 100, 25);
//            SelectPanel.add(word_close);
//
//            JLabel word_low = new JLabel("最低价", JLabel.CENTER);
//            word_low.setForeground(Color.DARK_GRAY);
//            word_low.setBackground(new Color(0, 0, 0, 0));
//            word_low.setBounds(100, 190, 100, 25);
//            SelectPanel.add(word_low);
//
//            JLabel word_volume = new JLabel("成交量", JLabel.CENTER);
//            word_volume.setForeground(Color.DARK_GRAY);
//            word_volume.setBackground(new Color(0, 0, 0, 0));
//            word_volume.setBounds(100, 240, 100, 25);
//            SelectPanel.add(word_volume);



            textField_open_s= new EmptyTextField(237, 34, 125, 36);
            SelectPanel.add(textField_open_s);
            textField_open_e= new EmptyTextField(431, 34, 125, 36);
            SelectPanel.add(textField_open_e);

            bopen = new MyButton(175,34,366,36);
            bopen.setIcon(new ImageIcon("ui/Text Edit.png"));
            SelectPanel.add(bopen);
            bopen.setVisible(false);


            textField_high_s= new EmptyTextField(237, 82, 125, 36);
            SelectPanel.add(textField_high_s);
            textField_high_e= new EmptyTextField(431, 82, 125, 36);
            SelectPanel.add(textField_high_e);

            bhigh = new MyButton(175,82,366,36);
            bhigh.setIcon(new ImageIcon("ui/Text Edit.png"));
            SelectPanel.add(bhigh);
            bhigh.setVisible(false);


            textField_close_s= new EmptyTextField(237, 130, 125, 36);
            SelectPanel.add(textField_close_s);
            textField_close_e= new EmptyTextField(431, 130, 125, 36);
            SelectPanel.add(textField_close_e);

            bclose = new MyButton(175,130,366,36);
            bclose.setIcon(new ImageIcon("ui/Text Edit.png"));
            SelectPanel.add(bclose);
            bclose.setVisible(false);


            textField_low_s= new EmptyTextField(237, 178, 125, 36);
            SelectPanel.add(textField_low_s);
            textField_low_e= new EmptyTextField(431, 178, 125, 36);
            SelectPanel.add(textField_low_e);

            blow = new MyButton(175,178,366,36);
            blow.setIcon(new ImageIcon("ui/Text Edit.png"));
            SelectPanel.add(blow);
            blow.setVisible(false);


            textField_volume_s= new EmptyTextField(237, 226, 125, 36);
            SelectPanel.add(textField_volume_s);
            textField_volume_e= new EmptyTextField(431, 226, 125, 36);
            SelectPanel.add(textField_volume_e);

            bvolume = new MyButton(175,226,366,36);
            bvolume.setIcon(new ImageIcon("ui/Text Edit.png"));
            SelectPanel.add(bvolume);
            bvolume.setVisible(false);

            textField_open_e.setVisible(false);
            textField_high_s.setVisible(false);
            textField_open_s.setVisible(false);
            textField_close_e.setVisible(false);
            textField_close_s.setVisible(false);
            textField_high_e.setVisible(false);
            textField_low_s.setVisible(false);
            textField_low_e.setVisible(false);
            textField_volume_s.setVisible(false);
            textField_volume_e.setVisible(false);

            box_open=new MyCheckBox(56,38);
            box_open.setIcon(new ImageIcon("ui/Unclicked.png"));
            box_open.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    if(box_open.isSelected()) {
                        box_open.setIcon(new ImageIcon("ui/Clicked.png"));
                        bopen.setVisible(true);
                        textField_open_e.setVisible(true);
                        textField_open_s.setVisible(true);
                    }else {
                        box_open.setIcon(new ImageIcon("ui/Unclicked.png"));
                        bopen.setVisible(false);
                        textField_open_e.setVisible(false);
                        textField_open_s.setVisible(false);
                    }

                }

            });
            SelectPanel.add(box_open);

            box_high=new MyCheckBox(56,86);
            box_high.setIcon(new ImageIcon("ui/Unclicked.png"));
            box_high.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    if(box_high.isSelected()) {
                        box_high.setIcon(new ImageIcon("ui/Clicked.png"));
                        bhigh.setVisible(true);
                        textField_high_e.setVisible(true);
                        textField_high_s.setVisible(true);
                    }else {
                        box_high.setIcon(new ImageIcon("ui/Unclicked.png"));
                        bhigh.setVisible(false);
                        textField_high_e.setVisible(false);
                        textField_high_s.setVisible(false);
                    }
                }

            });
            SelectPanel.add(box_high);

            box_close=new MyCheckBox(56,134);
            box_close.setIcon(new ImageIcon("ui/Unclicked.png"));
            box_close.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    if(box_close.isSelected()){
                        box_close.setIcon(new ImageIcon("ui/Clicked.png"));
                        bclose.setVisible(true);
                    textField_close_e.setVisible(true);
                    textField_close_s.setVisible(true);
                    }else {
                        box_close.setIcon(new ImageIcon("ui/Unclicked.png"));
                        bclose.setVisible(false);
                        textField_close_e.setVisible(false);
                        textField_close_s.setVisible(false);
                    }

                }

            });
            SelectPanel.add(box_close);
            box_low=new MyCheckBox(56,182);
            box_low.setIcon(new ImageIcon("ui/Unclicked.png"));
            box_low.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    if(box_low.isSelected()){
                        box_low.setIcon(new ImageIcon("ui/Clicked.png"));
                        blow.setVisible(true);
                        textField_low_e.setVisible(true);
                        textField_low_s.setVisible(true);
                    }else {
                        box_low.setIcon(new ImageIcon("ui/Unclicked.png"));
                        blow.setVisible(false);
                        textField_low_e.setVisible(false);
                        textField_low_s.setVisible(false);
                    }

                }

            });
            SelectPanel.add(box_low);
            box_volume=new MyCheckBox(56,230);
            box_volume.setIcon(new ImageIcon("ui/Unclicked.png"));
            box_volume.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    if(box_volume.isSelected()) {
                        box_volume.setIcon(new ImageIcon("ui/Clicked.png"));
                        bvolume.setVisible(true);
                        textField_volume_e.setVisible(true);
                        textField_volume_s.setVisible(true);
                    }else {
                        box_volume.setIcon(new ImageIcon("ui/Unclicked.png"));
                        bvolume.setVisible(false);
                        textField_volume_e.setVisible(false);
                        textField_volume_s.setVisible(false);
                    }
                }

            });
            SelectPanel.add(box_volume);

        }

    protected void close(){
        this.setVisible(false);
    }

    public ArrayList<SelectValueBean> getData(){
        return data;
    }




    }


