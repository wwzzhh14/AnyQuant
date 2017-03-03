package ui;


import main.Main;
import ui.myui.MyButton;
import ui.panels.*;
import vo.SelectValueBean;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;


/**
 * Created by dell on 2016/3/28.
 */
public class ControllerFrame extends JFrame{
    int type=0;
    ArrayList<SelectValueBean> data;
    String s,e;
    SelectUI ui;
    SelectPanel selectPanel;
    HeadPanel headPanel;
    DetailsPanel detailsPanel;
    HistoryDatePanel historyDatePanel;
    JPanel zPanel;
    public static String name;
    JButton button_exit;
    boolean isDraging;
    int xx, yy, X, Y;

    public ControllerFrame(){


        this.setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setWindowStyle ();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setUndecorated(true);
        GetLocation io = new GetLocation();
        this.setBounds(getLocation(scrSize.width, 1280), getLocation(scrSize.height, 720), 1280, 720);
        io.setX(getLocation(scrSize.width, 1280));
        io.setY(getLocation(scrSize.height, 720));


        this.setLayout(null);

        button_exit = new MyButton(5, 0, 30, 30);
        button_exit.setIcon(new ImageIcon("ui/return_inactive.png"));

        button_exit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent arg0) {

                button_exit.setIcon(new ImageIcon("ui/return.png"));

            }

            public void mouseExited(MouseEvent arg0) {
                button_exit.setIcon(new ImageIcon("ui/return-inactive.png"));

            }

            public void mouseClicked(MouseEvent arg0) {
                Main.frame.setVisible(true);
                close();
            }
        });
        this.add(button_exit);

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

        ImageIcon img = new ImageIcon("ui/null.png");// 这是背景图片
        JLabel imgLabel = new JLabel(img);// 将背景图放在标签里。

        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的
// LayeredPane面板里。
        imgLabel.setBounds(0, 0, this.getWidth(), this.getHeight());// 设置背景标签的位置
//	imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// 设置背景标签的位置
        Container cp = this.getContentPane();
        cp.setLayout(null); // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
        ((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了

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
    private int getLocation(int a, int b) {
        return (a - b) / 2;
    }
    private void init(){




    }
    public void close(){
        this.setVisible(false);
    }
    public void showForm(){





    }
    public void showdeepsearch(){





    }
    public void getdeepdata(){

    }
    public void showKLine(){

    }
    public void showLine(){


    }
    public void showFive(){


    }
    public void showFifteen(){

    }
    public void showThirty(){

    }
    public void showSixty(){

    }
public void show(String s,String e ){

    }
    public void showmacd(){

    }


}
