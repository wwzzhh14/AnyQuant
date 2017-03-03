package Demo.wjy.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created by Jiayiwu on 16/3/19.
 */
public class lineDemo {

     private static double maxValue,minValue=Double.MAX_VALUE;
    double high2Value = Double.MIN_VALUE;// 设置成交量的最大值
    double min2Value = Double.MAX_VALUE;// 设置成交量的最低值


    public  static void main(String args[]){
      new lineDemo().test();
    }

    private static XYDataset createDate(){
        TimeSeries timeSeries = new TimeSeries("");
        timeSeries.add(new Day(28, 9, 2007), 260659400/100);
        timeSeries.add(new Day(27, 9, 2007), 119701900/100);
        timeSeries.add(new Day(26, 9, 2007), 109719000/100);
        timeSeries.add(new Day(25, 9, 2007), 178492400/100);
        timeSeries.add(new Day(24, 9, 2007), 269978500/100);
        timeSeries.add(new Day(21, 9, 2007), 361042300/100);
        timeSeries.add(new Day(20, 9, 2007), 173912600/100);
        timeSeries.add(new Day(19, 9, 2007), 154622600/100);
        timeSeries.add(new Day(18, 9, 2007), 200661600/100);
        timeSeries.add(new Day(17, 9, 2007), 312799600/100);
        timeSeries.add(new Day(14, 9, 2007), 141652900/100);
        timeSeries.add(new Day(13, 9, 2007), 221260400/100);
        timeSeries.add(new Day(12, 9, 2007), 274795400/100);
        timeSeries.add(new Day(11, 9, 2007), 289287300/100);
        timeSeries.add(new Day(10, 9, 2007), 289063600/100);
        timeSeries.add(new Day(7, 9, 2007), 351575300/100);
        timeSeries.add(new Day(6, 9, 2007), 451357300/100);
        timeSeries.add(new Day(5, 9, 2007), 442421200/100);
        timeSeries.add(new Day(4, 9, 2007), 671942600/100);
        timeSeries.add(new Day(3, 9, 2007), 349647800/100);
        timeSeries.add(new Day(31, 8, 2007), 225339300/100);
        timeSeries.add(new Day(30, 8, 2007), 160048200/100);
        timeSeries.add(new Day(29, 8, 2007), 247341700/100);
        timeSeries.add(new Day(28, 8, 2007), 394975400/100);
        timeSeries.add(new Day(27, 8, 2007), 475797500/100);
        timeSeries.add(new Day(24, 8, 2007), 297679500/100);
        timeSeries.add(new Day(23, 8, 2007), 191760600/100);
        timeSeries.add(new Day(22, 8, 2007), 232570200/100);
        timeSeries.add(new Day(21, 8, 2007), 215693200/100);
        timeSeries.add(new Day(20, 8, 2007), 200287500/100);
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();
        timeSeriesCollection.addSeries(timeSeries);

        int seriesCount2 = timeSeriesCollection.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount2; i++) {
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

        return timeSeriesCollection;
    }

    public void test(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");






        TimeSeries series2=new TimeSeries("");// 对应时间成交量数据
        series2.add(new Day(28, 9, 2007), 260659400/100);
        series2.add(new Day(27, 9, 2007), 119701900/100);
        series2.add(new Day(26, 9, 2007), 109719000/100);
        series2.add(new Day(25, 9, 2007), 178492400/100);
        series2.add(new Day(24, 9, 2007), 269978500/100);
        series2.add(new Day(21, 9, 2007), 361042300/100);
        series2.add(new Day(20, 9, 2007), 173912600/100);
        series2.add(new Day(19, 9, 2007), 154622600/100);
        series2.add(new Day(18, 9, 2007), 200661600/100);
        series2.add(new Day(17, 9, 2007), 312799600/100);
        series2.add(new Day(14, 9, 2007), 141652900/100);
        series2.add(new Day(13, 9, 2007), 221260400/100);
        series2.add(new Day(12, 9, 2007), 274795400/100);
        series2.add(new Day(11, 9, 2007), 289287300/100);
        series2.add(new Day(10, 9, 2007), 289063600/100);
        series2.add(new Day(7, 9, 2007), 351575300/100);
        series2.add(new Day(6, 9, 2007), 451357300/100);
        series2.add(new Day(5, 9, 2007), 442421200/100);
        series2.add(new Day(4, 9, 2007), 671942600/100);
        series2.add(new Day(3, 9, 2007), 349647800/100);
        series2.add(new Day(31, 8, 2007), 225339300/100);
        series2.add(new Day(30, 8, 2007), 160048200/100);
        series2.add(new Day(29, 8, 2007), 247341700/100);
        series2.add(new Day(28, 8, 2007), 394975400/100);
        series2.add(new Day(27, 8, 2007), 475797500/100);
        series2.add(new Day(24, 8, 2007), 297679500/100);
        series2.add(new Day(23, 8, 2007), 191760600/100);
        series2.add(new Day(22, 8, 2007), 232570200/100);
        series2.add(new Day(21, 8, 2007), 215693200/100);
        series2.add(new Day(20, 8, 2007), 200287500/100);
        TimeSeriesCollection timeSeriesCollection1=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection1.addSeries(series2);

        double high2Value = Double.MIN_VALUE;// 设置成交量的最大值
        double min2Value = Double.MAX_VALUE;// 设置成交量的最低值



        TimeSeries series=new TimeSeries("");// 对应时间成交量数据
        series.add(new Day(28, 9, 2007), 260659400/100);
        series.add(new Day(27, 9, 2007), 119701900/100);
        series.add(new Day(26, 9, 2007), 109719000/100);
        series.add(new Day(25, 9, 2007), 178492400/100);
        series.add(new Day(24, 9, 2007), 269978500/100);
        series.add(new Day(21, 9, 2007), 361042300/100);
        series.add(new Day(20, 9, 2007), 173912600/100);
        series.add(new Day(19, 9, 2007), 154622600/100);
        series.add(new Day(18, 9, 2007), 200661600/100);
        series.add(new Day(17, 9, 2007), 312799600/100);
        series.add(new Day(14, 9, 2007), 141652900/100);
        series.add(new Day(13, 9, 2007), 221260400/100);
        series.add(new Day(12, 9, 2007), 274795400/100);
        series.add(new Day(11, 9, 2007), 289287300/100);
        series.add(new Day(10, 9, 2007), 289063600/100);
        series.add(new Day(7, 9, 2007), 351575300/100);
        series.add(new Day(6, 9, 2007), 451357300/100);
        series.add(new Day(5, 9, 2007), 442421200/100);
        series.add(new Day(4, 9, 2007), 671942600/100);
        series.add(new Day(3, 9, 2007), 349647800/100);
        series.add(new Day(31, 8, 2007), 225339300/100);
        series.add(new Day(30, 8, 2007), 160048200/100);
        series.add(new Day(29, 8, 2007), 247341700/100);
        series.add(new Day(28, 8, 2007), 394975400/100);
        series.add(new Day(27, 8, 2007), 475797500/100);
        series.add(new Day(24, 8, 2007), 297679500/100);
        series.add(new Day(23, 8, 2007), 191760600/100);
        series.add(new Day(22, 8, 2007), 232570200/100);
        series.add(new Day(21, 8, 2007), 215693200/100);
        series.add(new Day(20, 8, 2007), 200287500/100);
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection.addSeries(series2);

        int seriesCount2 = timeSeriesCollection.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount2; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);// 每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high2Value < timeSeriesCollection.getYValue(i,j)) {// 取第i个序列中的第j个数据项的值
                    high2Value = timeSeriesCollection.getYValue(i,j);
                }
                if (min2Value > timeSeriesCollection.getYValue(i, j)) {// 取第i个序列中的第j个数据项的值
                    min2Value = timeSeriesCollection.getYValue(i, j);
                }
            }
        }







        DateAxis x1Axis=new DateAxis();// 设置x轴，也就是时间轴
        x1Axis.setAutoRange(false);// 设置不采用自动设置时间范围
        try{
            x1Axis.setRange(dateFormat.parse("2007-08-20"),dateFormat.parse("2007-09-29"));// 设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
        }catch(Exception e){
            e.printStackTrace();
        }
        JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart(null,null,null,createDate());
//        JFreeChart jFreeChart1 = ChartFactory.createHistogram(null,null,null,createDate());
        x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
        x1Axis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
        x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,7));// 设置时间刻度的间隔，一般以周为单位
        x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));// 设置显示时间的格式
        x1Axis.setTickLabelPaint(Color.white);
        NumberAxis y1Axis=new NumberAxis();// 设定y轴，就是数字轴
        y1Axis.setAutoRange(false);// 不不使用自动设定范围
        y1Axis.setTickLabelPaint(Color.green);
        createDate();

        y1Axis.setRange(minValue*0.9, maxValue*1.1);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        y1Axis.setTickUnit(new NumberTickUnit((maxValue*1.1-minValue*0.9)/10));// 设置刻度显示的密度
        XYPlot plot1= jFreeChart.getXYPlot();

        plot1.setDomainAxis(x1Axis);
        plot1.setBackgroundPaint(Color.BLACK);
        plot1.setRangeAxis(y1Axis);
        plot1.setRangeGridlinePaint(Color.BLACK);
        plot1.setDomainGridlinePaint(Color.black);
        plot1.getRenderer().setSeriesPaint(0, Color.red);


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
        XYPlot plot2=new XYPlot(timeSeriesCollection,null,y2Axis,xyBarRender);// 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        plot2.setBackgroundPaint(Color.BLACK);
        plot2.setRangeGridlinePaint(Color.BLACK);
        plot2.setDomainGridlinePaint(Color.black);
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);
        combineddomainxyplot.add(plot1, 5);
        combineddomainxyplot.add(plot2,1);
        JFreeChart chart = new JFreeChart("AnyQuant", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        chart.setBackgroundPaint(Color.BLACK);
        ChartPanel content = new ChartPanel(chart);

        JFrame frame = new JFrame();
//        frame.setLayout(null);
        frame.add(content);
        frame.pack();
        frame.setVisible(true);
    }
}
