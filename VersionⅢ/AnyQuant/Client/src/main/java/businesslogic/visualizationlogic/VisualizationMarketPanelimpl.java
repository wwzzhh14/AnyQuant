package businesslogic.visualizationlogic;

import businesslogic.MarketBlLImpl;
import businesslogic.StockInfoBLImpl;
import businesslogic.predictionlogic.SimilarityAlgorithmImp;
import businesslogicservice.MarketBLService;
import businesslogicservice.StockInfoBLService;
import businesslogicservice.predictionlogicservice.PredictionBLService;
import businesslogicservice.visualizationlogicservice.VisualizationMarketData;
import businesslogicservice.visualizationlogicservice.VisualizationMarketPanel;
import businesslogicservice.visualizationlogicservice.VisualizationStockData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import util.TimeUtil;
import vo.KlineVO;
import vo.LineAndHistogramChartVO;
import vo.MAVO;
import vo.StockInfoVO;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jiayiwu on 16/3/20.
 */
public class VisualizationMarketPanelimpl implements VisualizationMarketPanel {

    private int dayNum = 7;

    public JPanel getKlineStockPanel(String start, String end, String marketNum) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        double highValue = Double.MIN_VALUE;// 设置K线数据当中的最大值
        double minValue = Double.MAX_VALUE;// 设置K线数据当中的最小值
        double high2Value = Double.MIN_VALUE;// 设置成交量的最大值
        double min2Value = Double.MAX_VALUE;// 设置成交量的最低值

        VisualizationMarketData data = new VisualizationMarketDataimpl();

        List<KlineVO> dataset = data.getKlineDate(start,end,marketNum);

        if(dataset.size()<=7)
            dayNum=1;
        else if(dataset.size()>7&&dataset.size()<15)
            dayNum=3;
        else
            dayNum=7;
        OHLCSeries series = new OHLCSeries("");// 高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
        TimeSeries series2=new TimeSeries("");
        TimeSeries series5avg = new TimeSeries("");
        TimeSeries series10avg = new TimeSeries("");
        TimeSeries series20avg = new TimeSeries("");

        MarketBLService avg = new MarketBlLImpl();
        List<MAVO> avg5 = avg.getMA(5,start,end,marketNum);
        List<MAVO> avg10 = avg.getMA(10,start,end,marketNum);
        List<MAVO> avg20 = avg.getMA(20,start,end,marketNum);

        for(int i = 0;i<avg5.size();i++){
            MAVO tem = avg5.get(i);
            series5avg.add(tem.getDate(),tem.getValue());
        }

        for(int i = 0;i<avg10.size();i++){
            MAVO tem = avg10.get(i);
            series10avg.add(tem.getDate(),tem.getValue());
        }

        for(int i = 0;i<avg20.size();i++){
            MAVO tem = avg20.get(i);
            series20avg.add(tem.getDate(),tem.getValue());
        }

        for (int i = 0;i<dataset.size();i++){
            KlineVO tem = dataset.get(i);

            series.add(tem.getDay(),tem.getOpen(),tem.getHigh(),tem.getLow(),tem.getClose());
            // 对应时间成交量数据
            series2.add(tem.getDay(),tem.getVolume()/100);


        }

        TimeSeriesCollection timeSeriesCollectionAvg=new TimeSeriesCollection();
        timeSeriesCollectionAvg.addSeries(series5avg);
        timeSeriesCollectionAvg.addSeries(series10avg);
        timeSeriesCollectionAvg.addSeries(series20avg);

        final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();
        seriesCollection.addSeries(series);
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection.addSeries(series2);

        // 获取K线数据的最高值和最低值
        int seriesCount = seriesCollection.getSeriesCount();// 一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = seriesCollection.getItemCount(i);// 每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (highValue < seriesCollection.getHighValue(i, j)) {// 取第i个序列中的第j个数据项的最大值
                    highValue = seriesCollection.getHighValue(i, j);
                }
                if (minValue > seriesCollection.getLowValue(i, j)) {// 取第i个序列中的第j个数据项的最小值
                    minValue = seriesCollection.getLowValue(i, j);
                }
            }
        }


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


        final CandlestickRenderer candlestickRender=new CandlestickRenderer();// 设置K线图的画图器，必须申明为final，后面要在匿名内部类里面用到
        candlestickRender.setUseOutlinePaint(true); // 设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);// 设置如何对K线图的宽度进行设定
        candlestickRender.setAutoWidthGap(0.001);// 设置各个K线图之间的间隔
        candlestickRender.setUpPaint(Color.RED);// 设置股票上涨的K线图颜色
        candlestickRender.setDownPaint(Color.GREEN);// 设置股票下跌的K线图颜色
        DateAxis x1Axis=new DateAxis();// 设置x轴，也就是时间轴
        x1Axis.setAutoRange(false);// 设置不采用自动设置时间范围
        try{
            x1Axis.setRange(dateFormat.parse(start),dateFormat.parse(DateAddOne(end)));// 设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
        }catch(Exception e){
            e.printStackTrace();
        }
        x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续


        x1Axis.setTickLabelPaint(Color.white);
        x1Axis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
        x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,dayNum));// 设置时间刻度的间隔，一般以周为单位
        x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));// 设置显示时间的格式
        NumberAxis y1Axis=new NumberAxis();// 设定y轴，就是数字轴

        y1Axis.setTickLabelPaint(Color.white);
        y1Axis.setAutoRange(false);// 不不使用自动设定范围
        y1Axis.setRange(minValue*0.9, highValue*1.1);// 设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        y1Axis.setTickUnit(new NumberTickUnit((highValue*1.1-minValue*0.9)/10));// 设置刻度显示的密度
//        XYPlot plot1=new XYPlot(seriesCollection,x1Axis,y1Axis,candlestickRender);// 设置画图区域对象
        XYPlot plot1=new XYPlot(null,x1Axis,y1Axis,null);
        StandardXYItemRenderer rendereravg = new StandardXYItemRenderer(
                StandardXYItemRenderer.SHAPES_AND_LINES);
        rendereravg.setShapesFilled(true);
        rendereravg.setSeriesPaint(0,new Color(253,188,64));
        rendereravg.setSeriesPaint(1,new Color(102,14,122));
        rendereravg.setSeriesPaint(2,Color.white);
        plot1.setDataset(0,seriesCollection);
        plot1.setRenderer(0,candlestickRender);
        plot1.setDataset(1,timeSeriesCollectionAvg);
        plot1.setRenderer(1,rendereravg);
        plot1.setBackgroundPaint(Color.BLACK);
        plot1.setRangeGridlinePaint(Color.black);
        plot1.setDomainGridlinePaint(Color.black);
//        plot1.setRangeGridlinePaint(null);
        XYBarRenderer xyBarRender=new XYBarRenderer(){
            private static final long serialVersionUID = 1L;// 为了避免出现警告消息，特设定此值
            public Paint getItemPaint(int i, int j){// 匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
                if(seriesCollection.getCloseValue(i,j)>seriesCollection.getOpenValue(i,j)){// 收盘价高于开盘价，股票上涨，选用股票上涨的颜色
                    return candlestickRender.getUpPaint();
                }else{
                    return candlestickRender.getDownPaint();
                }
            }};
        xyBarRender.setShadowVisible(false);
        xyBarRender.setMargin(0.1);// 设置柱形图之间的间隔
        NumberAxis y2Axis=new NumberAxis();// 设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y2Axis.setAutoRange(false);
        y2Axis.setRange(min2Value*0.9, high2Value*1.1);
        y2Axis.setTickUnit(new NumberTickUnit((high2Value*1.1-min2Value*0.9)/4));
        y2Axis.setTickLabelPaint(Color.white);

        // 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴
        XYPlot plot2=new XYPlot(timeSeriesCollection,null,y2Axis,xyBarRender);
        plot2.setRangeGridlinePaint(Color.black);
        plot2.setDomainGridlinePaint(Color.black);
        plot2.setBackgroundPaint(Color.BLACK);
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);// 建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1, 2);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, 1);// 添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(10);// 设置两个图形区域对象之间的间隔空间
        JFreeChart chart = new JFreeChart("AnyQuant", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        chart.setBackgroundPaint(Color.BLACK);
        ChartPanel content = new ChartPanel(chart);
        content.setVerticalAxisTrace(true);
        content.setHorizontalAxisTrace(true);
        return content;
    }



    //--------------------------------------------------获取界面一面板(折线图和直方图)————————————————————————————————————————————————————————————
    public JPanel getLineAndHistogramStockPanel(String start, String end, String marketNum) {



        double maxValue=Double.MIN_VALUE,minValue=Double.MAX_VALUE;
        double high2Value = Double.MIN_VALUE;// 设置成交量的最大值
        double min2Value = Double.MAX_VALUE;// 设置成交量的最低值
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        VisualizationMarketData data = new VisualizationMarketDataimpl();
        List<LineAndHistogramChartVO> list = data.getLineAndHistogramStockDate(start,end,marketNum);
        //价格图
        TimeSeries timeSeries = new TimeSeries("");
        //成交量图
        TimeSeries timeSeries1=new TimeSeries("");

        if(list.size()<=7)
            dayNum=1;
        else if(list.size()>7&&list.size()<15)
            dayNum=3;
        else
            dayNum=7;
        for(int i = 0;i<list.size();i++){
            LineAndHistogramChartVO vo = list.get(i);

            timeSeries.add(vo.getDay(),vo.getPrice());
            timeSeries1.add(vo.getDay(),vo.getDataNum());
        }
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection.addSeries(timeSeries);

        TimeSeriesCollection timeSeriesCollection1=new TimeSeriesCollection();// 保留成交量数据的集合
        timeSeriesCollection1.addSeries(timeSeries1);

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
        x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期(很多人都不知道有此方法)，使图形看上去连续
        x1Axis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置标记的位置
        x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,dayNum));// 设置时间刻度的间隔，一般以周为单位
        x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));// 设置显示时间的格式
        x1Axis.setTickLabelPaint(Color.white);

        NumberAxis y1Axis=new NumberAxis();// 设定y轴，就是数字轴
        y1Axis.setAutoRange(false);// 不不使用自动设定范围
        y1Axis.setTickLabelPaint(Color.green);
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

        XYPlot plot2=new XYPlot(timeSeriesCollection1,null,y2Axis,xyBarRender);// 建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        plot2.setBackgroundPaint(Color.BLACK);
        plot2.setRangeGridlinePaint(Color.BLACK);
        plot2.setDomainGridlinePaint(Color.black);
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);
        combineddomainxyplot.add(plot1, 5);
        combineddomainxyplot.add(plot2,1);

        JFreeChart chart = new JFreeChart("AnyQuant", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        chart.setBackgroundPaint(Color.BLACK);
        ChartPanel content = new ChartPanel(chart);

        content.setVerticalAxisTrace(true);
        content.setHorizontalAxisTrace(true);
        return content;
    }





    //--------------------------------------------------日期添加方法————————————————————————————————————————————————————————————
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
