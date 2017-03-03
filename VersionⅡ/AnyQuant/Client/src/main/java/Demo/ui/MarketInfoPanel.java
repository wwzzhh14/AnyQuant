package Demo.ui;

import Demo.ui.myui.DateChooserJButton;
import Demo.ui.myui.MyButton;
import Demo.ui.myui.MyComboBox;
import Demo.ui.myui.MyTable;
import businesslogic.MarketBlLImpl;
import businesslogicservice.MarketBLService;
import vo.BenchMarkInfoVO;
import vo.SelectValueBean;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by dell on 2016/3/9.
 */
//private String date;
//private double open;
//private double  high;
//private double  close;
//private double  low;
//private double  volume;
//private double  adj_price;
public class MarketInfoPanel extends JLabel implements ActionListener {
    MyTable table;
    MarketBLService marketController = new MarketBlLImpl();
    MyButton bt_query,bt_deep;
    public static JButton button_deep;
    String name;
    ArrayList<BenchMarkInfoVO> markinfoList;
    ArrayList<SelectValueBean> data;
    private SelectUI ui;
    DateChooserJButton t1, t2;
    final MyComboBox comboBox_m;
    public MarketInfoPanel() {
        this.setLayout(null);
        this.setBounds(336, 111, 922, 570);

        t1 = new DateChooserJButton();
        t1.setBounds(35, 0, 234, 45);
        this.add(t1);



        t2 = new DateChooserJButton();
        t2.setBounds(299, 0, 234, 45);
        this.add(t2);

        ArrayList<String> s_market = marketController.getAllBenchMark();
        String[] comboBoxStr = new String[s_market.size()];
        comboBoxStr = s_market.toArray(comboBoxStr);
        comboBox_m = new MyComboBox(564, 0, 100, 45, comboBoxStr);
        this.add(comboBox_m);


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
        String[] headers = {"日期", "开盘价", "最高价", "收盘价", "最低价", "成交量", "后复权价"};
        table = new MyTable(headers);
        table.setOpaque(true);
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
       markinfoList = marketController.getBenchMarkInfoByTimeOrByMarket(null, null, "hs300");
        showMarket(markinfoList);

    }

    public void showMarket( ArrayList<BenchMarkInfoVO> mlist) {
//        ArrayList<BenchMarkInfoVO> mlist = marketController.getBenchMarkInfoByTimeOrByMarket(s1, s2, m);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int rowCount = table.getRowCount();
        for (int k = 0; k < rowCount; k++)
            tableModel.removeRow(0);
        //"日期","开盘价","最高价","收盘价","最低价","成交量","后复权价"

        for (int i = 0; i < mlist.size(); i++) {
            Object[] rowData = {mlist.get(i).getDate(), mlist.get(i).getOpen(), mlist.get(i).getHigh(), mlist.get(i).getClose(), mlist.get(i).getLow(),
                    new BigDecimal(mlist.get(i).getVolume()).setScale(1), mlist.get(i).getAdj_price()};
            tableModel.addRow(rowData);

        }
    }
    public void showDeepSearch(){
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdt.format(t1.getDate());
        String endTime = sdt.format(t2.getDate());
        name = comboBox_m.getSelectedItem().toString();

//            String stock, String startTime, String endTime, SelectValueBean open, SelectValueBean high, SelectValueBean low,
// SelectValueBean close, SelectValueBean adj_price, SelectValueBean volume, SelectValueBean pe, SelectValueBean pb
        data = ui.getData();
        markinfoList = marketController.getBenchMarkInfoByTimeOrBySelect(startTime, endTime, name,data.get(0), data.get(1), data.get(2),
                data.get(3), data.get(4), null);
        showMarket(markinfoList);
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
            markinfoList = marketController.getBenchMarkInfoByTimeOrByMarket(
                    startTime,endTime,comboBox_m.getSelectedItem().toString());
            showMarket(markinfoList);
        }

        if (events.getSource() == bt_deep){
            ui = new SelectUI(1);
            ui.setVisible(true);
        }
    }
}


