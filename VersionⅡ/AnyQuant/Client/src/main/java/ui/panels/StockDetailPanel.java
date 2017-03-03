package ui.panels;

import javax.swing.table.DefaultTableModel;


import javax.swing.*;

import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import businesslogic.StockInfoBLImpl;
import businesslogicservice.StockInfoBLService;
import ui.FontFactory;
import ui.myui.*;

import vo.SelectValueBean;
import vo.StockInfoVO;

/**
 * Created by dell on 2016/3/7.
 */
public class StockDetailPanel extends JPanel implements SwitcherService {

//    private static final long serialVersionUID = 1L;


    MyButton bt_query;
    MyButton bt_deep;
   public static JButton button_deep;
    DateChooserJButton t1, t2;

    MyTable table;
    StockInfoBLService controller=new StockInfoBLImpl();

    ArrayList<StockInfoVO> stockinfoList;



    private MySwitcher switcher;
    private JFrame frame;


        public StockDetailPanel(JFrame frame) {

            this.frame = frame;
            this.setBackground(new Color(46,49,56));
            init(0, 30, true);
            this.setLayout(null);





//            t1 = new DateChooserJButton();
//            t1.setBounds(99, 10, 234, 45);
//            t1.setForeground(Color.CYAN);
//
//            this.add(t1);
//
//
//
//            t2 = new DateChooserJButton();
//            t2.setBounds(414, 10, 234, 45);
//   t2.setForeground(Color.cyan);
//            this.add(t2);



            String[] headers = {"日期", "开盘价", "最高价", "收盘价", "最低价", "交易量（股）", "交易金额（元）"};
            table = new MyTable(headers);
            table.setFont(FontFactory.getChinese(Font.PLAIN,14));
            table.setRowHeight(28);

            JScrollPane jsp = new JScrollPane(table);
            JTableHeader head = table.getTableHeader();
            head.setBackground(new Color(46,49,56));
            head.setForeground(Color.LIGHT_GRAY);
            head.setFont(FontFactory.getChinese(Font.PLAIN,17));
            jsp.setBounds(10,0, 990, 675);

            jsp.getViewport().setBackground(new Color(0, 0, 0, 0.3f));

            jsp.setOpaque(false);
            jsp.setBorder(BorderFactory.createEmptyBorder());
            jsp.setVisible(true);
            this.add(jsp);




            switcher=new MySwitcher();
            switcher.switchPanel(this,0,30,0,30);
            switcher=null;
            this.setVisible(true);




        }


    public void init(int x,int y,boolean stoped){

        this.setBounds(x, y, 1000, 690);
        frame.add(this);
    }




    public void show(String s) {

        stockinfoList=controller.getStockInfo(s);

      show(stockinfoList);
    }
    public void show(ArrayList<StockInfoVO> s) {
        stockinfoList=s;

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int rowCount = table.getRowCount();
        for (int k = 0; k < rowCount; k++)
            tableModel.removeRow(0);
        for (int i = 0; i < stockinfoList.size(); i++) {
//                    "日期", "开盘价", "最高价", "收盘价", "最低价","交易量（股）","交易金额（元）"
            Object[] rowData = {stockinfoList.get(i).getDate(), stockinfoList.get(i).getOpen(), stockinfoList.get(i).getHigh(), stockinfoList.get(i).getClose(),
                    stockinfoList.get(i).getLow(), stockinfoList.get(i).getVolume(), stockinfoList.get(i).getAdj_price()};
            tableModel.addRow(rowData);
        }

    }



}

