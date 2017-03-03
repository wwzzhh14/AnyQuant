package ui.panels;

import ui.FontFactory;
import ui.myui.MyTable;
import vo.RecommendOrange;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/5/4.
 */
public class OrangeRecommend extends JLabel{
    MyTable table;
   JLabel rank,pivot;
    public OrangeRecommend(List<RecommendOrange> oAna ){
        rank=new JLabel();
        rank.setOpaque(false);

        rank.setIcon( new ImageIcon("ui/rank.png"));
        rank.setBounds(100,0,280,30);
        this.add(rank);

        pivot=new JLabel();
        pivot.setOpaque(false);
        pivot.setIcon( new ImageIcon("ui/pivot.png"));
        pivot.setVisible(false);
        this.add(pivot);

        String[] headers = {"研究机构","评级日期 ","最新评级"};
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
        jsp.setBounds(0, 50, 500, 250);
        jsp.getViewport().setBackground(new Color(0, 0, 0, 0.3f));


        jsp.setOpaque(false);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.setVisible(true);
        this.add(jsp);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int rowCount = table.getRowCount();
        for (int k = 0; k < rowCount; k++)
            tableModel.removeRow(0);
        List<String> mess=new ArrayList<String>();

        for (int i = 0; i < oAna.size(); i++) {
            mess.add(oAna.get(i).getRecomendMessage());
            Object[] rowData = {oAna.get(i).getOrangeName(), oAna.get(i).getDate(), oAna.get(i).getRecomendMessage()};
            tableModel.addRow(rowData);

        }
        int a=getRank(mess);
        if(a!=5) {
            pivot.setBounds(120 + a * 56, 25, 20, 20);
            pivot.setVisible(true);
        }
    }
    int getRank( List<String> m){
        int a=0,b=0,c=0,d=0,e=0;
        int r=0;
        for(int i=0;i<m.size();i++){
            if(m.get(i).equals("卖出"))
                a++;
            else if(m.get(i).equals("减持"))
                b++;
            else if(m.get(i).equals("中性"))
                c++;
            else if(m.get(i).equals("增持"))
                d++;
            else if(m.get(i).equals("买入"))
                e++;

        }
        if(a>b&a>c&a>d&a>e) r=0;
        else if(b>a&b>c&b>d&b>e) r=1;
        else if(c>a&c>b&c>d&c>e) r=2;
        else if(d>a&d>c&d>b&d>e) r=3;
        else if(e>a&e>c&e>d&e>b) r=4;
        else return 5;
        return r;
    }
}
