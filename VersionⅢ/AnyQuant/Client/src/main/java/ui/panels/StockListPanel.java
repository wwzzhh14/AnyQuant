package ui.panels;

import businesslogic.StockInfoBLImpl;
import businesslogicservice.StockInfoBLService;
import config.ExchangePlace;
import ui.FontFactory;
import ui.MainFrame;
import ui.StockFrame;
import ui.myui.MyTable;
import vo.StockInfoVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by dell on 2016/3/7.
 */
public class StockListPanel extends JLabel{

    MyTable table;
    StockInfoBLService stockController=new StockInfoBLImpl();

    public static String name;
    JFrame frame;
    public StockListPanel(final MainFrame frame){
        this.setLayout(null);
        this.setBounds(336, 120, 922, 570);

        this.frame=frame;

        String[] headers = {"股票名称","股票代号","时间","开盘价","最高价","最低价","收盘价","后复权价","成交量","换手率","市净率","市盈率"};
        table = new MyTable(headers);
        table.setOpaque(false);
        table.setFont(FontFactory.getChinese(Font.PLAIN,14));
        table.setRowHeight(28);

//雅黑
        JScrollPane jsp = new JScrollPane(table);
        JTableHeader head = table.getTableHeader();
        head.setBackground(Color.darkGray);
        head.setForeground(Color.LIGHT_GRAY);
        head.setFont(FontFactory.getChinese(Font.PLAIN,17));
        jsp.setBounds(0, 0, 922, 505);
        jsp.getViewport().setBackground(new Color(0, 0, 0, 0.3f));


        jsp.setOpaque(false);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.setVisible(true);
        this.add(jsp);


        this.setBackground(null);
        showStockList(stockController.getStockByYearOrByPlace("2016", ExchangePlace.sh));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickCount = e.getClickCount();
                if (clickCount == 2) {

                    int row=table.getSelectedRow();
                    name=table.getValueAt(row,1).toString();

                    close();

//                    frame.showStock(name);
                    StockFrame stockFrame =new StockFrame(name);
stockFrame.setVisible(true);
//                 MainFrame.showStockDetailPanel(name);

                }
            }
        });

    }

    public void showStockList(ArrayList<StockInfoVO> stocklist){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int rowCount = table.getRowCount();
        for (int k = 0; k < rowCount; k++)
            tableModel.removeRow(0);
       // "股票代号","股票名称","时间","开盘价","最高价","最低价","收盘价","后复权价","成交量","换手率","市净率","市盈率"

        for (int i = 0; i < stocklist.size(); i++) {

            Object[] rowData = {stocklist.get(i).getName(), stocklist.get(i).getCodeNum(), stocklist.get(i).getDate(), stocklist.get(i).getOpen(), stocklist.get(i).getHigh(),  stocklist.get(i).getLow(), stocklist.get(i).getClose(), stocklist.get(i).getAdj_price(), stocklist.get(i).getVolume(), stocklist.get(i).getTurnover(),stocklist.get(i).getPe(),stocklist.get(i).getPb()};
            tableModel.addRow(rowData);

        }
    }
    public  void close(){
        frame.setVisible(false);
    }


}
