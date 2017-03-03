package ui.panels;

import businesslogic.MarketBlLImpl;
import businesslogicservice.MarketBLService;
import ui.FontFactory;
import ui.myui.*;
import vo.BenchMarkInfoVO;
import vo.SelectValueBean;
import vo.StockInfoVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class MarketInfoPanel extends JPanel{
    MyTable table;
    MarketBLService marketController = new MarketBlLImpl();


    ArrayList<BenchMarkInfoVO> markinfoList;


    public MarketInfoPanel() {
        this.setLayout(null);








        String[] headers = {"日期", "开盘价", "最高价", "收盘价", "最低价", "成交量", "后复权价"};
        table = new MyTable(headers);
        table.setOpaque(true);
        table.setFont(FontFactory.getChinese(Font.PLAIN,14));
        table.setRowHeight(28);

        JScrollPane jsp = new JScrollPane(table);
        JTableHeader head = table.getTableHeader();
        head.setBackground(Color.darkGray);
        head.setForeground(Color.LIGHT_GRAY);
head.setFont(FontFactory.getChinese(Font.PLAIN,17));
        jsp.setBounds(10,0, 990, 675);

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


}


