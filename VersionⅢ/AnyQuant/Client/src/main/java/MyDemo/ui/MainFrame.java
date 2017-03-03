package MyDemo.ui;

import MyDemo.wjy.demo.XChartPanel;
import MyDemo.ui.myui.MyButton;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


/**
 * Created by dell on 2016/3/7.
 */
public class MainFrame extends JFrame {


    private static final long serialVersionUID = 1L;


//     StockListPanel panel_list;
     MarketInfoPanel panel_market;
     StockDetailPanel panel_stock;

    MyButton button_stock;
    MyButton button_market;
    MyButton button_exit;
    static boolean MarketDataClick;
    static boolean StockInfoClick;


    boolean isDraging;
    int xx, yy, X0, Y0, X, Y;


    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setWindowStyle ();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setUndecorated(true);
        GetLocation io = new GetLocation();

        MarketDataClick = false;
        StockInfoClick = true;


        double[] xData = new double[] { 0.0, 1.0, 2.0 };
        double[] yData = new double[] { 2.0, 1.0, 0.0 };

        // Create Chart
        Chart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        XChartPanel panel = new XChartPanel(chart,336,120,922+336,570+120);
        panel.setBounds(336, 120, 922, 570);



        this.setBounds(getLocation(scrSize.width, 1280), getLocation(scrSize.height, 720), 1280, 720);
        io.setX(getLocation(scrSize.width, 1280));
        io.setY(getLocation(scrSize.height, 720));
//            }else{
//                this.setBounds(400 ,100, 1280, 720);
//            }

        this.setLayout(null);


//            panel_list=new StockListPanel(this);
//            this.add(panel_list);


        panel_market = new MarketInfoPanel();
        this.add(panel_market);
        panel_market.setVisible(false);



        button_stock = new MyButton(19, 174, 293, 55);
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
                MarketDataClick = false;
                StockInfoClick = true;
                button_stock.setIcon(new ImageIcon("ui/Stock_Info_active.png"));
                button_market.setIcon(new ImageIcon("ui/Market_Data_inactive.png"));
//                panel_list.setVisible(true);
                panel_market.setVisible(false);
               if(panel_stock!=null){
                   removePanel(panel_stock);
               }
                repaint();

            }
        });
        this.add(button_stock);
        this.add(panel);

        button_market = new MyButton(19, 120, 293, 55);
        button_market.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent arg0) {
                button_market.setIcon(new ImageIcon("ui/Market_Data_active.png"));
                }

            public void mouseClicked(MouseEvent arg0) {
                button_market.setIcon(new ImageIcon("ui/Market_Data_active.png"));
                button_stock.setIcon(new ImageIcon("ui/Stock_Info_inactive.png"));
                MarketDataClick = true;
                StockInfoClick = false;
//                panel_list.setVisible(false);
                panel_market.setVisible(true);
                if(panel_stock!=null) {
                    removePanel(panel_stock);
                }
                repaint();

            }

            public void mouseExited(MouseEvent arg0) {

                if (MarketDataClick)
                    button_market.setIcon(new ImageIcon("ui/Market_Data_active.png"));
                else
                    button_market.setIcon(new ImageIcon("ui/Market_Data_inactive.png"));

            }
        });

        this.add(button_market);


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
//
//        public String getCurrentLocation(int X, int Y){
//            return String.valueOf(X) + ";" + String.valueOf(Y);
//        }

    //	private  void setWindowStyle () {
//		try {
//			String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//			UIManager.setLookAndFeel(windows);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

   public  void showStock(String s){
       panel_stock=new StockDetailPanel(this);
      panel_stock.show(s);
//      panel_stock.setVisible(true);
//       panel_list.setVisible(false);
       panel_market.setVisible(false);

   }

     private void removePanel(JLabel l){
        this.remove(l);
    }

}
