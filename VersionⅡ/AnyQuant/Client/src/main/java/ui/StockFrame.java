package ui;

import businesslogic.StockInfoBLImpl;
import businesslogic.visualizationlogic.VisualizationStockPanelimpl;
import businesslogicservice.StockInfoBLService;
import businesslogicservice.visualizationlogicservice.VisualizationStockPanel;

import ui.panels.*;
import vo.NowTimeStockInfoVO;
import vo.StockInfoVO;


import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dell on 2016/3/21.
 */
public class StockFrame extends ControllerFrame implements ActionListener {
    HintPanel hintPanel = new HintPanel();
    KHintPanel kHintPanel=new KHintPanel();
    VisualizationStockPanel controller;
    StockInfoBLService dataController;

    public static JButton button_deep;



    public StockFrame(String name) {
        this.name = name;
        button_deep = new JButton();
        button_deep.addActionListener(this);
        this.add(button_deep);
        init();
    }

    /**
     * 透明度渐变启动界面
     *
     * @author cylong
     * @version 2014年12月12日 上午3:25:27
     */

    private void init() {
        controller = new VisualizationStockPanelimpl();
        dataController = new StockInfoBLImpl();


        zPanel = controller.getLineAndHistogramStockPanel("2016-01-22", "2016-03-22", name);
        zPanel.setBounds(0, 30, 1000, 690);

        this.add(zPanel);

        NowTimeStockInfoVO data = dataController.getNowTimeStockInfo(name);

        detailsPanel = new DetailsPanel();
        detailsPanel.setDate(data.getDate(), data.getTime(), data.getYestodEndPri(), data.getNowPri(), data.getTodayStartPri(), data.getTodayMax(), data.getTodayMin(), data.getIncrease(), data.getIncrePer(), data.getTraNumber(), data.getTraAmount());

        headPanel = new HeadPanel();
        headPanel.setData(data.getName(), data.getGid(), data.getNowPri(), data.getIncrease(), data.getIncrePer());

        historyDatePanel = new HistoryDatePanel();
        historyDatePanel.showStockList(dataController.getHistoryInfo(name));

        selectPanel = new SelectPanel(this);


        detailsPanel.setVisible(true);
        historyDatePanel.setVisible(true);
        headPanel.setVisible(true);
        selectPanel.setVisible(true);
        this.add(detailsPanel);
        this.add(headPanel);
        this.add(historyDatePanel);
        this.add(selectPanel);

    }

    public void close() {
        this.setVisible(false);
    }

    public void showForm() {
        type=3;
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);
        zPanel = new StockDetailPanel(this);
        zPanel.setBounds(0, 30, 1000, 690);
        zPanel.setOpaque(true);
        zPanel.setBackground(new Color(46, 49, 56));
        StockDetailPanel s = (StockDetailPanel) zPanel;
        s.show(name);
        zPanel.setVisible(true);
    }
public void getdeepdata(){
     ui=new SelectUI(0);
    ui.setVisible(true);
}
    public void showdeepsearch() {
data=ui.getData();
s=selectPanel.getstartDate();
        e=selectPanel.getenddate();
        ArrayList<StockInfoVO> stockinfoList= dataController.getStockInfoBySelect(name, s, e, data.get(0), data.get(1), data.get(2),
                data.get(3), null, data.get(4), null, null);
        StockDetailPanel s = (StockDetailPanel) zPanel;
        s.show(stockinfoList);
    }

    public void showKLine() {
        type = 1;
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(true);
        zPanel = controller.getKlineStockPanel("2016-01-22", "2016-03-22", name);
        zPanel.setBounds(0, 30, 1000, 640);
        kHintPanel.setBounds(0,670,1000,50);
        zPanel.setVisible(true);
        this.add(kHintPanel);
        this.add(zPanel);
    }

    public void showLine() {
        type = 0;
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);
        zPanel = controller.getLineAndHistogramStockPanel("2016-01-22", "2016-03-22", name);
        zPanel.setBounds(0, 30, 1000, 690);
        zPanel.setVisible(true);
        this.add(zPanel);

    }

    public void showFive() {
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(5);
        if (type == 1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else if (type == 0) {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        } else if (type == 2) {
            zPanel = controller.getMacdAndIncrementStockPanel(d.start, d.end, name, "hs300");
            zPanel.setBounds(0, 30, 1000, 600);
            hintPanel.setBounds(0, 630, 1000, 90);
            hintPanel.setVisible(true);
            this.add(hintPanel);
        }

        zPanel.setVisible(true);
        this.add(zPanel);

    }

    public void showFifteen() {
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(15);
        if (type == 1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else if (type == 0) {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        } else if (type == 2) {
            zPanel = controller.getMacdAndIncrementStockPanel(d.start, d.end, name, "hs300");
            zPanel.setBounds(0, 30, 1000, 600);
            hintPanel.setBounds(0, 630, 1000, 90);
            hintPanel.setVisible(true);
            this.add(hintPanel);
        }

        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void showThirty() {
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(30);
        if (type == 1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else if (type == 0) {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        } else if (type == 2) {
            zPanel = controller.getMacdAndIncrementStockPanel(d.start, d.end, name, "hs300");
            zPanel.setBounds(0, 30, 1000, 600);
            hintPanel.setBounds(0, 630, 1000, 90);
            hintPanel.setVisible(true);
            this.add(hintPanel);
        }

        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void showSixty() {
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);

        valueDate d = getDay(60);
        if (type == 1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else if (type == 0) {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        } else if (type == 2) {
            zPanel = controller.getMacdAndIncrementStockPanel(d.start, d.end, name, "hs300");
            zPanel.setBounds(0, 30, 1000, 600);
            hintPanel.setBounds(0, 630, 1000, 90);
            hintPanel.setVisible(true);
            this.add(hintPanel);
        }
        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void showmacd() {
        type = 2;
        zPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(20);

        zPanel = controller.getMacdAndIncrementStockPanel(d.start, d.end, name, "hs300");

        zPanel.setBounds(0, 30, 1000, 600);
        hintPanel.setBounds(0, 630, 1000, 90);
        hintPanel.setVisible(true);
        zPanel.setVisible(true);
        this.add(hintPanel);
        this.add(zPanel);
    }

    public void show(String s, String e) {
        this.s=s;
        this.e=e;
        zPanel.setVisible(false);
        hintPanel.setVisible(false);
        kHintPanel.setVisible(false);
        if (type == 1) {
            zPanel = controller.getKlineStockPanel(s, e, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else if (type == 0) {
            zPanel = controller.getLineAndHistogramStockPanel(s, e, name);
            zPanel.setBounds(0, 30, 1000, 690);
        } else if (type == 2) {
            zPanel = controller.getMacdAndIncrementStockPanel(s, e, name, "hs300");
            zPanel.setBounds(0, 30, 1000, 600);
            hintPanel.setBounds(0, 630, 1000, 90);
            hintPanel.setVisible(true);
            this.add(hintPanel);
        }else if(type==3){
            StockDetailPanel sd = (StockDetailPanel) zPanel;
            ArrayList<StockInfoVO> stockinfoList= dataController.getStockInfoByTime(name,s,e);
            sd.show(stockinfoList);
        }

        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void actionPerformed(ActionEvent events) {
        // TODO Auto-generated method stub

        // ///////////////////////////SEARCH////////////////////////////
        if (events.getSource() == button_deep) {
            showdeepsearch();
        }
    }

    private valueDate getDay(int num) {
        Calendar c = Calendar.getInstance();
        long date_2 = c.getTimeInMillis();
        long tem = 60 * 24 * num;
        long num1 = 1000 * 60 * tem;
        long date_1 = date_2 - num1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(date_1);
        String startTime = sdf.format(c.getTime());
        c.setTimeInMillis(date_2);
        String endTime = sdf.format(c.getTime());
        return new valueDate(startTime, endTime);
    }

    class valueDate {
        String start;
        String end;

        public valueDate(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }


    }
}