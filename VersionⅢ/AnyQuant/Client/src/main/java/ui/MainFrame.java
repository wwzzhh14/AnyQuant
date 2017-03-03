package ui;

import businesslogic.MarketBlLImpl;
import businesslogic.visualizationlogic.VisualizationRecommendPanelimpl;
import businesslogicservice.MarketBLService;
import businesslogicservice.visualizationlogicservice.VisualizationRecommendPanel;
import ui.myui.EmptyTextField;
import ui.myui.MyButton;
import ui.panels.StockListPanel;
import vo.QuadrantDiagramVO;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;


/**
 * Created by dell on 2016/3/7.
 */
public class MainFrame extends JFrame {


    private static final long serialVersionUID = 1L;


    StockListPanel panel_list;
    JPanel panel_recommend;
    JLabel panel_recommend_word;

    MyButton button_stock;
    MyButton button_market;
    MyButton button_recom;
    MyButton button_exit;
    static boolean MarketDataClick;
    static boolean RecommendClick;
    static boolean StockInfoClick;

    VisualizationRecommendPanel controller = new VisualizationRecommendPanelimpl();
    MarketBLService rcontroller = new MarketBlLImpl();
    boolean isDraging;
    int xx, yy, X, Y;


    public MainFrame() {
        MarketDataClick = false;
        RecommendClick = false;
        StockInfoClick = true;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setUndecorated(true);
        GetLocation io = new GetLocation();


        this.setBounds(getLocation(scrSize.width, 1280), getLocation(scrSize.height, 720), 1280, 720);
        io.setX(getLocation(scrSize.width, 1280));
        io.setY(getLocation(scrSize.height, 720));


        this.setLayout(null);


        panel_list = new StockListPanel(this);
        this.add(panel_list);

        panel_recommend = controller.getScatterPanel();
        panel_recommend.setBounds(336, 120, 922, 350);
;
        this.add(panel_recommend);
        panel_recommend.setVisible(false);




        panel_recommend_word = new JLabel();

        panel_recommend_word.setLayout(null);
        panel_recommend_word.setBounds(314,480,922,210);

        panel_recommend_word.setIcon(new ImageIcon("ui/recommend.png"));

        ArrayList<QuadrantDiagramVO> list = rcontroller.getQuadrantDiagramInfo();


        String highbig = "";
        String highsmall = "";
        String lowbig = "";
        String lowsmall = "";
        for (QuadrantDiagramVO vo:list){
            if (vo.getPe()>=0 && vo.getSum()>=0){
                highbig = highbig+vo.getName()+"; ";
            }
            else if (vo.getPe()>=0 && vo.getSum()<=0){
                highsmall = highsmall+vo.getName()+"; ";
            }
            else if (vo.getPe()<=0 && vo.getSum()>=0){
                lowbig = lowbig+vo.getName()+"; ";
            }
            else if (vo.getPe()<=0 && vo.getSum()<=0){
                lowsmall = lowsmall+vo.getName()+"; ";
            }
        }
        EmptyTextField textField_highbig = new EmptyTextField(354,15,582,24);
        if (highbig == ""){
            textField_highbig.setText(highbig);
        }else{
            textField_highbig.setText(highbig.substring(0,highbig.length()-2));
        }
        textField_highbig.setEditable(false);
        textField_highbig.setForeground(new Color(255,192,65));
        panel_recommend_word.add(textField_highbig);

        EmptyTextField textField_highsmall = new EmptyTextField(354,63,582,24);
        if (highsmall == ""){
            textField_highsmall.setText(highsmall);
        }else{
            textField_highsmall.setText(highsmall.substring(0,highsmall.length()-2));
        }
        textField_highsmall.setEditable(false);
        textField_highsmall.setForeground(new Color(255,192,65));
        panel_recommend_word.add(textField_highsmall);

        EmptyTextField textField_lowbig = new EmptyTextField(354,111,582,24);
        if (lowbig == ""){
            textField_lowbig.setText(lowbig);
        }else{
            textField_lowbig.setText(lowbig.substring(0,lowbig.length()-2));
        }
        textField_lowbig.setEditable(false);
        textField_lowbig.setForeground(new Color(255,192,65));
        panel_recommend_word.add(textField_lowbig);

        EmptyTextField textField_lowsmall = new EmptyTextField(354,159,582,24);
        if (lowsmall == ""){
            textField_lowsmall.setText(lowsmall);
        }else{
            textField_lowsmall.setText(lowsmall.substring(0,lowsmall.length()-2));
        }
        textField_lowsmall.setEditable(false);
        textField_lowsmall.setForeground(new Color(255,192,65));
        panel_recommend_word.add(textField_lowsmall);


        this.add(panel_recommend_word);
        panel_recommend_word.setVisible(false);


        button_stock = new MyButton(19, 174, 293, 55);
        button_stock.setIcon(new ImageIcon("ui/Stock_Info_active.png"));
        button_stock.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent arg0) {

                button_stock.setIcon(new ImageIcon("ui/Stock_Info_active.png"));

            }

            public void mouseExited(MouseEvent arg0) {
                if (StockInfoClick)
                    button_stock.setIcon(new ImageIcon("ui/Stock_Info_active.png"));
                else
                    button_stock.setIcon(new ImageIcon("ui/Stock_Info_inactive.png"));

            }

            public void mouseClicked(MouseEvent arg0) {



                button_stock.setIcon(new ImageIcon("ui/Stock_Info_active.png"));
                button_market.setIcon(new ImageIcon("ui/Market_Data_inactive.png"));
               button_recom.setIcon(new ImageIcon("ui/Plate_Recommend_inactive.png"));

                panel_recommend.setVisible(false);
                panel_recommend_word.setVisible(false);
                panel_list.setVisible(true);
                StockInfoClick = true;
                MarketDataClick = false;
                RecommendClick = false;

            }
        });
        this.add(button_stock);


        button_market = new MyButton(19, 120, 293, 55);
        button_market.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent arg0) {
                button_market.setIcon(new ImageIcon("ui/Market_Data_active.png"));
            }

            public void mouseClicked(MouseEvent arg0) {
                StockInfoClick=true;
                button_stock.setIcon(new ImageIcon("ui/Stock_Info_active.png"));
                close();

                MarketFrame frame = new MarketFrame("hs300");
                frame.setVisible(true);

                MarketDataClick = true;
                RecommendClick = false;
            }

            public void mouseExited(MouseEvent arg0) {


                    button_market.setIcon(new ImageIcon("ui/Market_Data_inactive.png"));

            }
        });

        this.add(button_market);

        button_recom = new MyButton(19, 228, 293, 55);
        button_recom.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent arg0) {

                button_recom.setIcon(new ImageIcon("ui/Plate_Recommend_active.png"));

            }

            public void mouseExited(MouseEvent arg0) {
                if (RecommendClick)
                    button_recom.setIcon(new ImageIcon("ui/Plate_Recommend_active.png"));
                else
                    button_recom.setIcon(new ImageIcon("ui/Plate_Recommend_inactive.png"));

            }

            public void mouseClicked(MouseEvent arg0) {
                button_recom.setIcon(new ImageIcon("ui/Plate_Recommend_active.png"));
                button_stock.setIcon(new ImageIcon("ui/Stock_Info_inactive.png"));
                button_market.setIcon(new ImageIcon("ui/Market_Data_inactive.png"));


                panel_list.setVisible(false);
                panel_recommend.setVisible(true);
                panel_recommend_word.setVisible(true);

                StockInfoClick = false;
                MarketDataClick = false;
                RecommendClick = true;
            }
        });
        this.add(button_recom);

        //the button for action exit
        button_exit = new MyButton(1217, 34, 35, 35);

        button_exit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent arg0) {

                button_exit.setIcon(new ImageIcon("ui/close_active.png"));

            }

            public void mouseExited(MouseEvent arg0) {
                button_exit.setIcon(new ImageIcon("ui/close_inactive.png"));

            }

            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }
        });
        this.add(button_exit);
        ImageIcon img = new ImageIcon("ui/AnyQuant test.png");// 这是背景图片
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
                    io.setX(X);
                    io.setY(Y);
                }
            }
        });

        new HyalineValue().start(); // 透明渐变启动界面
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

    private void close() {
        this.setVisible(false);
    }




}


