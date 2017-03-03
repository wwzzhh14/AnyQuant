package ui.panels;


import ui.FontFactory;
import ui.myui.MyTable;
import vo.HistoryInfoVO;

import javax.swing.*;

import javax.swing.table.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by dell on 2016/3/21.
 */
public class HistoryDatePanel extends JLabel{
    MyTable table;
    String[] headers = {"时间","成交价","成交量","金额"};;
    boolean [] up;

    public HistoryDatePanel(){
        this.setLayout(null);
        this.setBounds(1010,345,260,400);




        table = new MyTable(headers);
        table.setOpaque(true);
        table.setFont(FontFactory.getChinese(Font.PLAIN,12));
        table.setBackground(new Color(46,49,56));
        table.setRowHeight(20);


        JScrollPane jsp = new JScrollPane(table);
        JTableHeader head = table.getTableHeader();
        head.setBackground(Color.darkGray);
        head.setForeground(Color.LIGHT_GRAY);
        head.setFont(FontFactory.getChinese(Font.PLAIN,14));
        jsp.setBounds(0, 0, 260, 360);
        jsp.getViewport().setBackground((new Color(0, 0, 0, 0.3f)));


        jsp.setOpaque(false);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.setVisible(true);
        setTableHeaderWidth();
        this.add(jsp);


        this.setBackground(null);



    }
    /**调整表格列的宽度。*/          //1
    private void setTableHeaderWidth()
    {

        int[] columnWidth = { 70, 50, 70, 90 };
        // 创建表格数据模型
        DefaultTableModel model = new DefaultTableModel(headers, 15);
        table.setModel(model);// 设置表格数据模型
        TableColumnModel columnModel = table.getColumnModel();// 获取列模型
        int count = columnModel.getColumnCount();// 获取列数量
        for (int i = 0; i < count; i++) {// 遍历列
            TableColumn column = columnModel.getColumn(i);// 获取列对象
            column.setPreferredWidth(columnWidth[i]);// 以数组元素设置列的宽度
        }


    }




    public void showStockList(ArrayList<HistoryInfoVO> stocklist){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int rowCount = table.getRowCount();
        for (int k = 0; k < rowCount; k++)
            tableModel.removeRow(0);
        // "时间","成交价","成交量","金额"
        up=new boolean[stocklist.size()];
        for (int i = 0; i < stocklist.size(); i++) {


            Object[] rowData = {stocklist.get(i).getDate(), stocklist.get(i).getPrice(), stocklist.get(i).getVolume(), stocklist.get(i).getSum()};

            tableModel.addRow(rowData);
          up[i]=stocklist.get(i).isUp();

        }

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if(up[row])
                    setForeground(Color.red);
                else
                    setForeground(Color.green);
                return super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
            }
        };

            table.getColumn(headers[1]).setCellRenderer(tcr);


        TableColumn tableColumn = table.getColumn("时间");
        DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
        backGroundColor.setForeground(Color.white);
        tableColumn.setCellRenderer(backGroundColor);


        TableColumn tableColumn2 = table.getColumn("成交量");
        DefaultTableCellRenderer backGroundColor2 = new DefaultTableCellRenderer();
        backGroundColor2.setForeground(Color.yellow);
        tableColumn2.setCellRenderer(backGroundColor2);

        TableColumn tableColumn3 = table.getColumn("金额");
        DefaultTableCellRenderer backGroundColor3 = new DefaultTableCellRenderer();
        backGroundColor3.setForeground(Color.yellow);
        tableColumn3.setCellRenderer(backGroundColor3);








}





    public  void close(){
        this.setVisible(false);
    }


}
