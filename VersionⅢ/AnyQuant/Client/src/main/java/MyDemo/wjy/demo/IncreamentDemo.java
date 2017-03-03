package MyDemo.wjy.demo;

import businesslogic.MarketBlLImpl;
import businesslogic.StockInfoBLImpl;
import businesslogicservice.MarketBLService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import vo.IncreasementVO;
import vo.macdVO;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jiayiwu on 16/4/6.
 */
public class IncreamentDemo {
    private int dayNum=7;
    public JPanel getLineAndHistogramStockPanel(String start, String end, String codeNum,String markNum) {



        double maxValue=Double.MIN_VALUE,minValue=Double.MAX_VALUE;
        double high2Value = Double.MIN_VALUE;// 设置成交量的最大值
        double min2Value = Double.MAX_VALUE;// 设置成交量的最低值

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        MarketBLService market = new MarketBlLImpl();
        StockInfoBLImpl stock = new StockInfoBLImpl();

        List<IncreasementVO>marketList = market.getMartketInscreasment(start,end,markNum);
        List<IncreasementVO>stocktList = stock.getStockInscreasment(start,end,codeNum);
        List<macdVO>macdList = stock.getMACDData(start,end,codeNum,"MACD");

        //价格图
        TimeSeries timeSeriesStock = new TimeSeries("Stock");
        TimeSeries timeSeriesMarket = new TimeSeries("The Stock Market");
        //成交量图
        TimeSeries timeSeriesMacd=new TimeSeries("");
        if(marketList.size()<=7)
            dayNum=1;
        else if(marketList.size()>7&&marketList.size()<15)
            dayNum=3;
        else
            dayNum=7;

        //股票数据
        for(int i = 0;i<marketList.size();i++){
            IncreasementVO vo = marketList.get(i);
            timeSeriesMarket.add(vo.getDate(),vo.getIncreasement());
        }

        for(int i = 0;i<stocktList.size();i++){
            IncreasementVO vo = stocktList.get(i);
            timeSeriesStock.add(vo.getDate(),vo.getIncreasement());
        }
        for(int i = 0;i<macdList.size();i++){
            macdVO vo = macdList.get(i);
            timeSeriesMacd.add(vo.getDate(),vo.getValue());
        }

        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection.addSeries(timeSeriesMarket);
        timeSeriesCollection.addSeries(timeSeriesStock);

        TimeSeriesCollection timeSeriesCollection1=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection1.addSeries(timeSeriesMacd);

        int seriesCount = timeSeriesCollection.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);// 每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (maxValue < timeSeriesCollection.getYValue(i,j)) {// 取第i个序列中的第j个数据项的值
                    maxValue = timeSeriesCollection.getYValue(i,j);
                }
                if (minValue > timeSeriesCollection.getYValue(i, j)) {// 取第i个序列中的第j个数据项的值
                    minValue = timeSeriesCollection.getYValue(i, j);
                }
            }
        }

        int seriesCount1 = timeSeriesCollection1.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount1; i++) {
            int itemCount = timeSeriesCollection1.getItemCount(i);// 每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high2Value < timeSeriesCollection1.getYValue(i,j)) {// 取第i个序列中的第j个数据项的值
                    high2Value = timeSeriesCollection1.getYValue(i,j);
                }
                if (min2Value > timeSeriesCollection1.getYValue(i, j)) {// 取第i个序列中的第j个数据项的值
                    min2Value = timeSeriesCollection1.getYValue(i, j);
                }
            }
        }


        DateAxis x1Axis=new DateAxis();
        x1Axis.setAutoRange(false);
        try{
            x1Axis.setRange(dateFormat.parse(start),dateFormat.parse(DateAddOne(end)));
        }catch(Exception e){
            e.printStackTrace();
        }

        JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart(null,null,null,timeSeriesCollection);
        x1Axis.setTickLabelPaint(Color.green);
        x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
        x1Axis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
        x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,dayNum));// 设置时间刻度的间隔，一般以周为单位
        x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));// 设置显示时间的格式

        NumberAxis y1Axis=new NumberAxis();// 设定y轴，就是数字轴
        y1Axis.setAutoRange(false);// 不不使用自动设定范围
        y1Axis.setTickLabelPaint(Color.green);
        y1Axis.setRange(minValue*0.9, maxValue*1.1);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        y1Axis.setTickUnit(new NumberTickUnit((maxValue*1.1-minValue*0.9)/10));// 设置刻度显示的密度
        y1Axis.setNumberFormatOverride(NumberFormat.getPercentInstance());

        XYPlot plot1= jFreeChart.getXYPlot();
        plot1.setDomainAxis(x1Axis);
        plot1.setBackgroundPaint(Color.BLACK);
        plot1.setRangeAxis(y1Axis);
        plot1.setRangeGridlinePaint(Color.BLACK);
        plot1.setDomainGridlinePaint(Color.black);

        plot1.getRenderer().setSeriesPaint(0, Color.red);
        plot1.getRenderer().setSeriesPaint(1, new Color(32,193,219));

        NumberAxis y2Axis=new NumberAxis();// 设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y2Axis.setAutoRange(false);
        y2Axis.setRange(min2Value*0.9, high2Value*1.1);
        y2Axis.setTickUnit(new NumberTickUnit((high2Value*1.1-min2Value*0.9)/4));
        y2Axis.setTickLabelPaint(new Color(46,133,225));
        XYBarRenderer xyBarRender=new XYBarRenderer(){
            private static final long serialVersionUID = 1L;// 为了避免出现警告消息，特设定此值
            public Paint getItemPaint(int i, int j){// 匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
                return new Color(46,133,225);
            }

        };
        xyBarRender.setMargin(0.1);
        xyBarRender.setBarPainter(new StandardXYBarPainter());
        xyBarRender.setShadowVisible(false);

        XYPlot plot2=new XYPlot(timeSeriesCollection1,null,y2Axis,xyBarRender);// 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        plot2.setBackgroundPaint(Color.BLACK);
        plot2.setRangeGridlinePaint(Color.black);
        plot2.setDomainGridlinePaint(Color.black);
        plot2.setDomainZeroBaselinePaint(Color.red);
        plot2.setDomainZeroBaselineVisible(true);
        plot2.setRangeZeroBaselinePaint(Color.red);
        plot2.setRangeZeroBaselineVisible(true);

        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);
        combineddomainxyplot.add(plot1, 4);
        combineddomainxyplot.add(plot2,1);

        JFreeChart chart = new JFreeChart("AnyQuant", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        chart.setBackgroundPaint(Color.black);
        ChartPanel content = new ChartPanel(chart);
        content.setVerticalAxisTrace(true);
        content.setHorizontalAxisTrace(true);
        return content;
    }


    private String DateAddOne(String date){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(date);
            c.setTime(d);
            long time =c.getTimeInMillis();
            time+=24*60*60*1000;
            c.setTimeInMillis(time);

            return sdf.format(d.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  sdf.format(new Date());
    }
}
