package ui;

import businesslogic.MarketBlLImpl;

import businesslogic.visualizationlogic.VisualizationMarketPanelimpl;

import businesslogicservice.MarketBLService;

import businesslogicservice.visualizationlogicservice.VisualizationMarketPanel;

import ui.panels.*;
import vo.BenchMarkInfoVO;
import vo.NowTimeBenchMarkInfoVO;



import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dell on 2016/3/28.
 */
public class MarketFrame extends ControllerFrame implements ActionListener{

    public static JButton button_deep;
    KHintPanel kHintPanel=new KHintPanel();
    VisualizationMarketPanel controller;
    MarketBLService dataController;


    public MarketFrame(String name) {
        this.name = name;
        button_deep = new JButton();
        button_deep.addActionListener(this);
        this.add(button_deep);

        init();
    }

    private void init() {
        controller = new VisualizationMarketPanelimpl();
        dataController = new MarketBlLImpl();


        zPanel = controller.getLineAndHistogramStockPanel("2016-01-22", "2016-03-22", name);
        zPanel.setBounds(0, 30, 1000, 690);
        this.add(zPanel);

        NowTimeBenchMarkInfoVO data = dataController.getNowTimeBenchMarkInfo();

        detailsPanel = new DetailsPanel();
        detailsPanel.setDate(data.getTime().substring(0, 10), data.getTime().substring(10), data.getYesPri(), data.getNowpri(), data.getOpenPri(), data.getHighPri(), data.getLowpri(), data.getIncrease(), data.getIncrePer(), data.getDealNum(), data.getDealPri());

        headPanel = new HeadPanel();
        headPanel.setData(data.getName(),null, data.getNowpri(), data.getIncrease(), data.getIncrePer());

        historyDatePanel = new HistoryDatePanel();
        historyDatePanel.showStockList(dataController.getHistoryInfo(name));

        selectPanel = new SelectPanel(this);
selectPanel.offmacd();

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
        kHintPanel.setVisible(false);
        zPanel = new MarketInfoPanel();
        zPanel.setBounds(0, 30, 1000, 690);
        zPanel.setOpaque(true);
        zPanel.setBackground(new Color(46,49,56));
        this.add(zPanel);
        zPanel.setVisible(true);


    }
    public void getdeepdata(){
        ui=new SelectUI(1);
        ui.setVisible(true);
    }
    public void showdeepsearch() {
        data=ui.getData();
s=selectPanel.getstartDate();
        e=selectPanel.getenddate();
        ArrayList<BenchMarkInfoVO>  markinfoList = dataController.getBenchMarkInfoByTimeOrBySelect(s, e, name,data.get(0), data.get(1), data.get(2),
                data.get(3), data.get(4), null);
        MarketInfoPanel s = (MarketInfoPanel) zPanel;
        s.showMarket(markinfoList);
    }

    public void showKLine() {
        type = 1;
        zPanel.setVisible(false);

        zPanel = controller.getKlineStockPanel("2016-01-22", "2016-03-22", name);
        zPanel.setBounds(0, 30, 1000, 640);
        kHintPanel.setBounds(0,670,1000,50);
        kHintPanel.setVisible(true);
        zPanel.setVisible(true);
        this.add(kHintPanel);
        this.add(zPanel);
    }

    public void showLine() {
        type = 0;
        zPanel.setVisible(false);
        kHintPanel.setVisible(false);
        zPanel = controller.getLineAndHistogramStockPanel("2016-01-22", "2016-03-22", name);
        zPanel.setBounds(0, 30, 1000, 690);
        zPanel.setVisible(true);
        this.add(zPanel);

    }

    public void showFive() {
        zPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(5);
        if (type==1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        }

        zPanel.setVisible(true);
        this.add(zPanel);

    }

    public void showFifteen() {
        zPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(15);
        if (type==1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        }

        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void showThirty() {
        zPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(30);
        if (type==1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        }

        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void showSixty() {
        zPanel.setVisible(false);
        kHintPanel.setVisible(false);
        valueDate d = getDay(60);
        if (type==1) {
            zPanel = controller.getKlineStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else {
            zPanel = controller.getLineAndHistogramStockPanel(d.start, d.end, name);
            zPanel.setBounds(0, 30, 1000, 690);
        }

        zPanel.setVisible(true);
        this.add(zPanel);
    }

    public void show(String s, String e) {
        zPanel.setVisible(false);
kHintPanel.setVisible(false);
        if (type==1) {
            zPanel = controller.getKlineStockPanel(s, e, name);
            zPanel.setBounds(0, 30, 1000, 640);
            kHintPanel.setBounds(0,670,1000,50);
            kHintPanel.setVisible(true);
            this.add(kHintPanel);
        } else if(type==0){
            zPanel = controller.getLineAndHistogramStockPanel(s, e, name);
            zPanel.setBounds(0, 30, 1000, 690);
        }else if(type==3){
            MarketInfoPanel sd = (MarketInfoPanel) zPanel;
            ArrayList<BenchMarkInfoVO> markInfolist= dataController.getBenchMarkInfoByTimeOrByMarket(s,e,"hs300");
            sd.showMarket(markInfolist);
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


