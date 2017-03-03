package MyDemo.wzh.demo;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.*;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class KLineCombineChart {









    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

        double high2Value = Double.MIN_VALUE;// 设置成交量的最大值
        double min2Value = Double.MAX_VALUE;// 设置成交量的最低值



        TimeSeries series2=new TimeSeries("");// 对应时间成交量数据
        Day d = new Day(28,9,2007);
        series2.add(d,10000000);
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
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection.addSeries(series2);


//---------------------------------------------------------------------------

    //-------------------------------------------------------------------------


//        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
//
//        lineDataset.addValue(100, "出售资产", "北京");
//        lineDataset.addValue(200, "出售资产", "田径");
//        lineDataset.addValue(600, "出售资产", "苏州");
//        lineDataset.addValue(400, "出售资产", "杭州");
//        lineDataset.addValue(700, "出售资产", "大连");
//        lineDataset.addValue(600, "出售资产", "合肥");


        // 获取K线数据的最高值和最低值

        // 获取最高值和最低值
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
        x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
        x1Axis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
        x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,7));// 设置时间刻度的间隔，一般以周为单位
        x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));// 设置显示时间的格式
        x1Axis.setTickLabelPaint(Color.white);

        NumberAxis y1Axis=new NumberAxis();// 设定y轴，就是数字轴


//        plot1.setRangeGridlinePaint(null);
        XYBarRenderer xyBarRender=new XYBarRenderer(){
            private static final long serialVersionUID = 1L;// 为了避免出现警告消息，特设定此值
          };
        xyBarRender.setMargin(0.1);// 设置柱形图之间的间隔
        NumberAxis y2Axis=new NumberAxis();// 设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y2Axis.setAutoRange(false);
        y2Axis.setRange(min2Value*0.9, high2Value*1.1);
        y2Axis.setTickUnit(new NumberTickUnit((high2Value*1.1-min2Value*0.9)/4));
        y2Axis.setTickLabelPaint(Color.white);
        XYPlot plot2=new XYPlot(timeSeriesCollection,null,y2Axis,xyBarRender);// 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        plot2.setBackgroundPaint(Color.BLACK);
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);// 建立一个恰当的联合图形区域对象，以x轴为共享轴
        // 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, 1);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(10);// 设置两个图形区域对象之间的间隔空间
        JFreeChart chart = new JFreeChart("AnyQuant", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        chart.setBackgroundPaint(Color.BLACK);
//        CombinedDomainXYPlot categoryplot = (CombinedDomainXYPlot) chart.getPlot();
//        categoryplot.setDataset( lineDataset);
//        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
//        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
//        categoryplot.setRenderer(1, lineandshaperenderer);
//        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        ChartPanel content = new ChartPanel(chart);
        content.setMouseZoomable(true,true);
        content.setBounds(200,200,100,100);
        //保持K线图和折线图对齐


        JFrame frame = new JFrame();
//        frame.setLayout(null);
        frame.add(content);
        frame.pack();
        frame.setVisible(true);
    }
}