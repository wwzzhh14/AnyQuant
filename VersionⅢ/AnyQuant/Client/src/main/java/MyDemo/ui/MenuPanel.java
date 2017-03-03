package MyDemo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dell on 2016/3/7.
 */
public class MenuPanel extends JPanel implements ActionListener {
    JButton button_stock;
    JButton button_market;
    private MainFrame frame;

    public  MenuPanel(MainFrame frame) {
        this.frame=frame;
        this.setBounds(0, 50, 200, 670);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(new Color(0, 0, 0, 0.25f));
        init();

    }
  private void init(){
      //the label for button_return
      button_stock = new JButton();
      button_stock.setBounds(10, 75, 200, 50);
      button_stock.setForeground(Color.WHITE);
      button_stock.setBackground(new Color(46, 52, 101));
      button_stock.setText("股票信息");
      button_stock.addActionListener(this);
      this.add(button_stock);
      button_stock.setVisible(false);

      button_market = new JButton();
      button_market.setBounds(10, 125, 200, 50);
      button_market.setForeground(Color.WHITE);
      button_market.setBackground(new Color(46, 52, 101));
      button_market.setText("大盘数据");
      button_market.addActionListener(this);
      this.add(button_market);
      button_market.setVisible(false);

  }


    public void actionPerformed(ActionEvent events) {
        if (events.getSource() == button_stock) {
          StockListPanel stockListPanel =new StockListPanel(frame);
            stockListPanel.setVisible(true);
        }
        else if (events.getSource() == button_market) {
        	StockDetailPanel stockDetailPanel=new StockDetailPanel(frame);
        }

}
}