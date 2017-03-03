package ui;

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
import ui.myui.*;

import vo.SelectValueBean;
import vo.StockInfoVO;

/**
 * Created by dell on 2016/3/7.
 */
public class StockDetailPanel extends JLabel implements ActionListener , SwitcherService {

//    private static final long serialVersionUID = 1L;

    MyButton bt_query;
    MyButton bt_deep;
   public static JButton button_deep;
    DateChooserJButton t1, t2;
    MyTable table;
    StockInfoBLService controller=new StockInfoBLImpl();
    String name;
    ArrayList<StockInfoVO> stockinfoList;
    ArrayList<SelectValueBean> data;
    private SelectUI ui;

    private MySwitcher switcher;
    private MainFrame frame;


        public StockDetailPanel(MainFrame frame) {
            this.frame = frame;

            init(336, 120, true);



            this.setLayout(null);




            t1 = new DateChooserJButton();
            t1.setBounds(99, 0, 234, 35);

            this.add(t1);



            t2 = new DateChooserJButton();
            t2.setBounds(414, 0, 234, 35);

            this.add(t2);

            bt_query = new MyButton(716, 0, 94, 44);
            bt_query.setIcon(new ImageIcon("ui/EasySearch.png") );

            bt_query.setVisible(true);
            this.add(bt_query);
            bt_query.addActionListener(this);

            button_deep=new JButton();
            button_deep.addActionListener(this);
            this.add(button_deep);


            bt_deep = new MyButton(810, 0, 94, 44);
            bt_deep.setIcon(new ImageIcon("ui/Deep_Search.png") );

            bt_deep.setVisible(true);
            this.add(bt_deep);

            bt_deep.addActionListener(this);

            String[] headers = {"日期", "开盘价", "最高价", "收盘价", "最低价", "交易量（股）", "交易金额（元）"};
            table = new MyTable(headers);
            table.setFont(new Font("雅黑", Font.PLAIN, 14));
            table.setRowHeight(28);

            JScrollPane jsp = new JScrollPane(table);
            JTableHeader head = table.getTableHeader();
            head.setBackground(Color.darkGray);
            head.setForeground(Color.LIGHT_GRAY);
            head.setFont(new Font("雅黑", Font.PLAIN, 17));
            jsp.setBounds(0, 65, 922, 505);

            jsp.getViewport().setBackground(new Color(0, 0, 0, 0.3f));

            jsp.setOpaque(false);
            jsp.setBorder(BorderFactory.createEmptyBorder());
            jsp.setVisible(true);
            this.add(jsp);

            this.setBackground(null);


            switcher=new MySwitcher();
            switcher.switchPanel(this,900,120,336,120);
            switcher=null;
            this.setVisible(true);

        }


    public void init(int x,int y,boolean stoped){

        this.setBounds(x, y, 922, 570);
        frame.add(this);
    }

    public void actionPerformed(ActionEvent events) {
        // TODO Auto-generated method stub

        // ///////////////////////////SEARCH////////////////////////////
        if(events.getSource() == button_deep){
            showDeepSearch();
        }

        if (events.getSource() == bt_query) {
            SimpleDateFormat sdt=new SimpleDateFormat("yyyy-MM-dd");
            String startTime=sdt.format(t1.getDate());
            String endTime=sdt.format(t2.getDate());
            name= StockListPanel.name;
            stockinfoList = controller.getStockInfoByTime(name,
                    startTime,endTime);
            show(stockinfoList);
        }

        if (events.getSource() == bt_deep){
            ui = new SelectUI(0);
            ui.setVisible(true);
        }
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

    public void showDeepSearch() {


        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdt.format(t1.getDate());
        String endTime = sdt.format(t2.getDate());
        name = StockListPanel.name;

//            String stock, String startTime, String endTime, SelectValueBean open, SelectValueBean high, SelectValueBean low,
// SelectValueBean close, SelectValueBean adj_price, SelectValueBean volume, SelectValueBean pe, SelectValueBean pb
        data = ui.getData();
        stockinfoList = controller.getStockInfoBySelect(name, startTime, endTime, data.get(0), data.get(1), data.get(2),
                data.get(3), null, data.get(4), null, null);
        show(stockinfoList);
    }



}

