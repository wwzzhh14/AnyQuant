package MyDemo.wjy.demo;

/**
 * Created by Jiayiwu on 16/3/17.
 */
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;

public class CandlestickVolume {
    static ChartPanel charPanel = null;
    private static final Color COLOR_close = new Color(41, 36, 33);// 收盘价颜色 象牙黑
    private static final Color COLOR_MA5 = new Color(138, 43, 226);// 5日均线颜色 紫
    private static final Color COLOR_MA10 = new Color(30, 144, 255);// 10日均线颜色 蓝
    private static final Color COLOR_MA15 = new Color(255, 165, 0);// 15日均线颜色
    // 土黄
    private static final Color COLOR_MA20 = new Color(255, 0, 255);// 20日均线颜色
    // 深红
    private static final Color COLOR_MA30 = new Color(240, 230, 140);// 30日均线颜色
    // 黄褐
    private static final Color COLOR_MA60 = new Color(0, 0, 128);// 60日均线颜色
    // 藏青
    private static final Color COLOR_MA120 = new Color(34, 193, 34);// 120日均线颜色
    // 森林绿
    private static final Color COLOR_MAVOL5 = new Color(138, 43, 226);// 5日均线颜色
    // 金黄
    private static final Color COLOR_MAVOL10 = new Color(255, 0, 0);// 10日均线颜色 红
    private static int W_P = 3;// 价格宽度
    private static int W_V = 1;// 成交量宽度
    private static int WIDTH = 900;
    private static int HEIGHT = 600;
    private static int P_H = (int) (HEIGHT * (1.0 * W_P / (W_P + W_V)));// 价格高度
    private static int V_H = (int) (HEIGHT * (1.0 * W_V / (W_P + W_V)));// 成交量高度
    private static Color upColor = Color.RED.brighter();// 股票上涨的颜色 亮红色 收盘价大于开盘价
    private static Color downColor = Color.GREEN.brighter();// 股票下跌的颜色 亮绿色
    // 开盘价小于收盘价

    public static void main(String[] args) {

        JFreeChart chart = buildChart();// 建表
        ChartFrame chartFrame = new ChartFrame("股票行情分析图", chart);
        charPanel = chartFrame.getChartPanel();
        // 创建菜单栏
        JMenuBar mnb = new JMenuBar();
        // 创建菜单
  /* 1 */JMenu mnuSystem = new JMenu("系统(S)");
  /* 2 */JMenu mnuSystem1 = new JMenu("工具(T)");
        // 创建菜单项
        JMenuItem mniCopy = new JMenuItem("复制");
        mniCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));//设置快捷键Ctrl+C
        mniCopy.addActionListener(new MyListener());
        JMenuItem mniSaveas = new JMenuItem("另存为");
        mniSaveas.addActionListener(new MyListener());
        JMenuItem mniPrint = new JMenuItem("打印");
        mniPrint.addActionListener(new MyListener());


        JMenuItem mniEditChartProperties = new JMenuItem("属性");
        mniEditChartProperties.addActionListener(new MyListener());
        // 将菜单项添加到菜单中
        mnuSystem.add(mniCopy);
        mnuSystem.add(mniSaveas);
        mnuSystem.addSeparator();
        mnuSystem.add(mniPrint);
        mnuSystem.addSeparator();
        mnuSystem1.add(mniEditChartProperties );
        // 将菜单添加到菜单栏中
  /* 1 */mnb.add(mnuSystem);
  /* 1 */mnb.add(mnuSystem1);

        // 将菜单栏挂靠在框架上
        chartFrame.setJMenuBar(mnb);
        chartFrame.setSize(WIDTH, HEIGHT);// 设置尺寸
        chartFrame.setVisible(true);// 设置为可见
    }
    public static JFreeChart buildChart() {
        // X共用轴
        DateAxis domainAxis = new DateAxis();// 设置区域轴（即为X轴） 也就是 时间轴
        // 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期,使图形看上去连续
        domainAxis.setTimeline(SegmentedTimeline
                .newMondayThroughFridayTimeline());// SegmentedTimeline为分割时间线的意思
        domainAxis.setAutoTickUnitSelection(false);// 设置不采用自动选择刻度值
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// 设置日期标签的位置
        // （需要将AutoTickUnitSelection设false）
        domainAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// 设置标准的时间刻度单位
        // 数据轴的数据标签（可以只显示整数标签，需要将AutoTickUnitSelection设false）
        //domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));//
        // 设置时间刻度的间隔，一般以日为单位
        domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 7));// 设置时间刻度的间隔，一般以周为单位
        //domainAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 30));
        // 设置时间刻度的间隔，一般以月为单位
        domainAxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));// 设置显示时间的格式
        // 即用简单的时间显示格式（月-天）取代所要设置的时间显示格式
        domainAxis.setLowerMargin(0.02D);// 下跌范围
        domainAxis.setUpperMargin(0.02D);// 上涨范围
        // 价格区(Plot)
        // Y坐标轴
        NumberAxis priceAxis = new NumberAxis() {// Y坐标轴(共用) 设置价格轴（即为Y轴）为数字轴
            private static final long serialVersionUID = -8962863563900391155L;

            @Override
            // 取代
            public Paint getTickLabelPaint() {// 绘画 获取作记号的绘画标记
                return Color.BLACK;
            }

            @Override
            public NumberFormat getNumberFormatOverride() {// 设定数据显示格式
                return new DecimalFormat("0.00");
            }

            @Override
            public boolean getAutoRangeIncludesZero() {// 不自动包含0价格区
                return false;
            }
        };
        // priceAxis.Y轴
        // 价格图区(共用区)ss

        XYPlot pricePlot = new XYPlot(null, domainAxis, priceAxis, null);
        // 数据1
        final OHLCDataset priceDataset = getPriceDataSet("000001");// MSFT
        // 烛行图设定
        CandlestickRenderer priceRenderer = new CandlestickRenderer();
        priceRenderer.setSeriesPaint(0, Color.black);// 蜡烛框架颜色
        priceRenderer.setDrawVolume(true);// 画成交量
        priceRenderer.setUpPaint(upColor); // 上涨
        priceRenderer.setDownPaint(downColor);// 下跌
        // 收盘价的线
        // 1天的priceDataset
        XYDataset xyDataset1 = createAverageDataset(priceDataset, 1, 0);// 获取数据源
        XYSplineRenderer xyRender1 = new XYSplineRenderer() {//创建一个新的渲染器指定的精度
            private static final long serialVersionUID = 1859309003413288563L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_close;
            }
        };
        // 均价图设定
        // 5天均价
        XYDataset xyDataset5 = createAverageDataset(priceDataset, 5, 0);// 获取数据源
        XYSplineRenderer xyRender5 = new XYSplineRenderer() {
            private static final long serialVersionUID = 1859309003413288563L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA5;
            }
        };
        // 10天均价
        XYDataset xyDataset10 = createAverageDataset(priceDataset, 10, 0);
        XYSplineRenderer xyRender10 = new XYSplineRenderer() {
            private static final long serialVersionUID = 1664318080312451661L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA10;
            }
        };
        // 15天均价
        XYDataset xyDataset15 = createAverageDataset(priceDataset, 15, 0);
        XYSplineRenderer xyRender15 = new XYSplineRenderer() {
            private static final long serialVersionUID = 5338323939520574140L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA15;
            }
        };
        // 20天均价
        XYDataset xyDataset20 = createAverageDataset(priceDataset, 20, 0);
        XYSplineRenderer xyRender20 = new XYSplineRenderer() {
            private static final long serialVersionUID = 2041484824762200102L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA20;
            }
        };

        // 30天均价
        XYDataset xyDataset30 = createAverageDataset(priceDataset, 30, 0);
        XYSplineRenderer xyRender30 = new XYSplineRenderer() {
            private static final long serialVersionUID = 2041484824762200102L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA30;
            }
        };
        // 60天均价
        XYDataset xyDataset60 = createAverageDataset(priceDataset, 60, 0);
        XYSplineRenderer xyRender60 = new XYSplineRenderer() {
            private static final long serialVersionUID = 2041484824762200102L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA60;
            }
        };
        // 120天均价
        XYDataset xyDataset120 = createAverageDataset(priceDataset, 120, 0);
        XYSplineRenderer xyRender120 = new XYSplineRenderer() {
            private static final long serialVersionUID = 2041484824762200102L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MA120;
            }
        };
        Paint gradientP = new GradientPaint(0, 0, new Color(252, 252, 200), 0,
                P_H, new Color(252, 252, 252));// 画 价格梯度 渐变色 下至上（100,170,200）
        Paint gradientV = new GradientPaint(0, 0, new Color(252, 252, 252), 0,
                V_H, new Color(200, 150, 130));// 画 成交量梯度 渐变色 上(189,252,201)至下
        pricePlot.setDataset(0, priceDataset);
        pricePlot.setRenderer(0, priceRenderer);
        pricePlot.setDataset(1, xyDataset5);
        pricePlot.setRenderer(1, xyRender5);
        pricePlot.setDataset(2, xyDataset10);
        pricePlot.setRenderer(2, xyRender10);
        pricePlot.setDataset(3, xyDataset15);
        pricePlot.setRenderer(3, xyRender15);
        pricePlot.setDataset(4, xyDataset20);
        pricePlot.setRenderer(4, xyRender20);
        pricePlot.setDataset(5, xyDataset30);
        pricePlot.setRenderer(5, xyRender30);
        pricePlot.setDataset(6, xyDataset60);
        pricePlot.setRenderer(6, xyRender60);
        pricePlot.setDataset(7, xyDataset120);
        pricePlot.setRenderer(7, xyRender120);
        pricePlot.setDataset(8, xyDataset1);
        pricePlot.setRenderer(8, xyRender1);
        pricePlot.setBackgroundPaint(gradientP);// 背景 渐变色
        // 交易量区(Plot)
        // IntervalXYDataset volumeDataset = getVolumeDataset(priceDataset,24L *
        // 60 * 60 * 1000);
        XYDataset volumeDataset = getVolumeDataset(priceDataset,
                24L * 60 * 60 * 1000);
        // Each bar is 24 hours wide（每条为24小时宽）.XY数据间隔设置
        // PS:在24后加一个L表示24为长整型
        NumberAxis volumeAxis = new NumberAxis() {
            private static final long serialVersionUID = -4910537548590143802L;

            @Override
            public NumberFormat getNumberFormatOverride() {// 设定显示格式
                return new DecimalFormat("0.00E00") {
                    private static final long serialVersionUID = 8224078094493778288L;

                    @Override
                    public StringBuffer format(double number,
                                               StringBuffer result, FieldPosition fieldPosition) {// 缓冲
                        // 表示
                        String sb = super.format(number, result, fieldPosition)
                                .toString();
                        String[] ctx = sb.split("E");// 上下文 分割成“E”
                        // ctx=Context上下文
                        String first = ctx[0];
                        String second = ctx[1];
                        double fValue = Double.valueOf(first);
                        int sValue = Integer.valueOf(second);
                        if (fValue <= 0.001d) {
                            return new StringBuffer("0");
                        }
                        int tcount = 0;// 计数
                        String suffix = "";// 后缀/词尾
                        if (sValue < 3) {
                            tcount = sValue;
                            suffix = "";
                        } else if (sValue < 6) {
                            tcount = sValue - 3;
                            suffix = "K";
                        } else if (sValue < 9) {
                            tcount = sValue - 6;
                            suffix = "M";
                        } else if (sValue < 12) {
                            tcount = sValue - 9;
                            suffix = "G";
                        } else if (sValue < 15) {
                            tcount = sValue - 12;
                            suffix = "T";
                        } else {
                            return new StringBuffer("NaN");// 规范定义:NaN不等于任何数包括自己;float和doule存在特殊的值NaN
                        }
                        String symbol = "";// 记号
                        if (tcount == 0) {
                            symbol = "0.00";
                        } else if (tcount == 1) {
                            symbol = "00.0";
                        } else if (tcount == 2) {
                            symbol = "000";
                        }
                        DecimalFormat format = new DecimalFormat(symbol);// 小数的表示

                        return new StringBuffer(format.format(fValue
                                * Math.pow(10, tcount))).append(suffix);// 缓冲表示
                        // 表示为数学形式的计数.附上后缀
                    }
                };
            }

            @Override
            public Paint getTickLabelPaint() {
                return Color.black;
            }
        };
        // XYPlot pricePlot = new XYPlot(null, domainAxis, priceAxis, null);

        XYBarRenderer volumeRenderer = new XYBarRenderer() {
            private static final long serialVersionUID = -4237832644518701189L;

//   @Override

            public Paint getSeriesPaint(int series) {

                return Color.blue.brighter();
            }


        };// 实例化一个类{}类后加；

        // volumePlot区域
        XYPlot volumePlot = new XYPlot(volumeDataset, null, volumeAxis,
                volumeRenderer);
        // XYPlot plot2=new
        // XYPlot(timeSeriesCollection,null,y2Axis,xyBarRender);
        volumePlot.setBackgroundPaint(gradientV);// 背景颜色为渐变色

        // 5天均量价
        XYDataset xyDatasetVOL5 = createAverageDataset(volumeDataset, 5, 0);
        XYSplineRenderer xyRenderVOL5 = new XYSplineRenderer() {
            private static final long serialVersionUID = 2041484824762200102L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MAVOL5;
            }
        };
        // 10天均量价
        XYDataset xyDatasetVOL10 = createAverageDataset(volumeDataset, 10, 0);
        XYSplineRenderer xyRenderVOL10 = new XYSplineRenderer() {
            private static final long serialVersionUID = 2041484824762200102L;

            @Override
            public Boolean getSeriesShapesVisible(int series) {
                return false;
            }

            @Override
            public Paint getSeriesPaint(int series) {
                return COLOR_MAVOL10;
            }
        };
        volumePlot.setDataset(0, volumeDataset);
        volumePlot.setRenderer(0, volumeRenderer);
        volumePlot.setDataset(1, xyDatasetVOL5);
        volumePlot.setRenderer(1, xyRenderVOL5);
        volumePlot.setDataset(2, xyDatasetVOL10);
        volumePlot.setRenderer(2, xyRenderVOL10);
        // 把各个区统一起来
        CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(domainAxis);
        mainPlot.add(pricePlot, 2);
        mainPlot.add(volumePlot, 1);
        mainPlot.setGap(5.0d);// 设定各区之间的间隔
        // 自定义图例
        LegendItemCollection legendItemCollection = createLegendItems();
        mainPlot.setFixedLegendItems(legendItemCollection);

        return new JFreeChart("深发展A", null, mainPlot, true);
    }

    // 自定义图例
    private static LegendItemCollection createLegendItems() {
        LegendItemCollection legenditemcollection1 = new LegendItemCollection();
        LegendItem legenditem1 = new LegendItem("MA5", "-", "5日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA5);
        LegendItem legenditem2 = new LegendItem("MA10", "-", "10日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA10);
        LegendItem legenditem3 = new LegendItem("MA15", "-", "15日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA15);
        LegendItem legenditem4 = new LegendItem("MA20", "-", "20日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA20);
        LegendItem legenditem5 = new LegendItem("MA30", "-", "30日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA30);
        LegendItem legenditem6 = new LegendItem("MA60", "-", "60日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA60);
        LegendItem legenditem7 = new LegendItem("MA120", "-", "120日均价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MA120);
        LegendItem legenditem8 = new LegendItem("close", "-", "收盘线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_close);
        LegendItem legenditem11 = new LegendItem("MAVOL5", "-", "5日均量价线", null,
                Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MAVOL5);
        LegendItem legenditem12 = new LegendItem("MAVOL10", "-", "10日均量价线",
                null, Plot.DEFAULT_LEGEND_ITEM_BOX, COLOR_MAVOL10);

        legenditemcollection1.add(legenditem1);
        legenditemcollection1.add(legenditem2);
        legenditemcollection1.add(legenditem3);
        legenditemcollection1.add(legenditem4);
        legenditemcollection1.add(legenditem5);
        legenditemcollection1.add(legenditem6);
        legenditemcollection1.add(legenditem7);
        legenditemcollection1.add(legenditem8);
        legenditemcollection1.add(legenditem11);
        legenditemcollection1.add(legenditem12);


        return legenditemcollection1;// 插图条的采集

    }

    protected static OHLCDataset getPriceDataSet(String symbol) {
        List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
//        try {
//   String strUrl = "http://ichart.finance.yahoo.com/table.csv?s="
//     + symbol + "&a=8&b=1&c=2009&d=12&e=30&f=2009&ignore=.csv";
//   URL url = new URL(strUrl);
//   BufferedReader in = new BufferedReader(new InputStreamReader(url
//     .openStream()));
//            BufferedReader in = new BufferedReader(new FileReader("c://SZ000001.TXT"));
//            DateFormat df = new SimpleDateFormat("y-M-d");
//            String inputLine;
//            in.readLine();
//            while ((inputLine = in.readLine()) != null) {
//                StringTokenizer st = new StringTokenizer(inputLine, ",");
//                Date date = df.parse(st.nextToken());
//                double open = Double.parseDouble(st.nextToken());//nextToken() 用于返回下一个匹配的字段
//                double high = Double.parseDouble(st.nextToken());
//                double low = Double.parseDouble(st.nextToken());
//                double close = Double.parseDouble(st.nextToken());
//                double volume = Double.parseDouble(st.nextToken());
//                // double adjClose = Double.parseDouble( st.nextToken() );
//                OHLCDataItem item = new OHLCDataItem(date, open, high, low,
//                        close, volume);//数据条的显示
//                dataItems.add(item);
//            }
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        dataItems.add(new OHLCDataItem(new Date(2015-9-28), 9.2, 9.58, 9.16, 9.34,260659400/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-27), 8.9, 9.06, 8.83, 8.96, 119701900/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-26), 9.0, 9.1, 8.82, 9.04, 109719000/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-25), 9.25, 9.33, 8.88, 9.00, 178492400/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-24), 9.05, 9.50, 8.91, 9.25, 269978500/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-23), 8.68, 9.05, 8.40, 9.00, 361042300/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-22), 8.68, 8.95, 8.50, 8.69, 173912600/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-21), 8.80, 8.94, 8.50, 8.66, 154622600/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-20), 8.88, 9.17, 8.69, 8.80, 200661600/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-19), 8.26, 8.98, 8.15, 8.89, 312799600/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-18), 8.44, 8.45, 8.13, 8.33, 141652900/100));
        dataItems.add(new OHLCDataItem(new Date(2015-9-17), 8.13, 8.46, 7.97, 8.42, 221260400/100));








        Collections.reverse(dataItems);
        OHLCDataItem[] data = dataItems.toArray(new OHLCDataItem[dataItems
                .size()]);
        return new DefaultOHLCDataset("", data);
    }

    protected static IntervalXYDataset getVolumeDataset( // XY数据源间隔 获取成交量数据源
                                                         final OHLCDataset priceDataset, final long barWidthInMilliseconds) {
        return new AbstractIntervalXYDataset() {
            private static final long serialVersionUID = 2289539752052175826L;

            public int getSeriesCount() {
                return priceDataset.getSeriesCount();
            }

            @SuppressWarnings("unchecked")
            public Comparable getSeriesKey(int series) {
                return priceDataset.getSeriesKey(series) + "-Volume";
            }

            public int getItemCount(int series) {
                return priceDataset.getItemCount(series);
            }

            public Number getX(int series, int item) {
                return priceDataset.getX(series, item);
            }

            public Number getY(int series, int item) {
                return priceDataset.getVolume(series, item);
            }

            public Number getStartX(int series, int item) {
                return priceDataset.getX(series, item).doubleValue()
                        - barWidthInMilliseconds / 2;
            }

            public Number getEndX(int series, int item) {
                return priceDataset.getX(series, item).doubleValue()
                        + barWidthInMilliseconds / 2;
            }
            public Number getStartY(int series, int item) {
                return new Double(0.0d);
            }

            public Number getEndY(int series, int item) {
                return priceDataset.getVolume(series, item);
            }
        };
    }

    /**
     * 转化为N日均线
     *
     * @param day
     * @return
     */
    private static XYDataset createAverageDataset(final XYDataset source,
                                                  final int day, final int daySkip) {
        XYDataset xyDataset = MovingAverage.createMovingAverage(source, day
                        + "日均线", 24L * 60 * 60 * 1000 * day, // day天为一个周期
                24L * 60 * 60 * 1000 * daySkip); // 最开始的daySkip天跳过
        return xyDataset;
    }


}
class MyListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        // System.out.println(e.getActionCommand());
        CandlestickVolume panel = new CandlestickVolume();
        if ("另存为".equals(e.getActionCommand())) {

            try {
                panel.charPanel.doSaveAs();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }else if ("复制".equals(e.getActionCommand())) {

            panel.charPanel.doCopy();
        }else if ("打印".equals(e.getActionCommand())) {

            panel.charPanel.createChartPrintJob();
        }else if("属性".equals(e.getActionCommand())){
            panel.charPanel.doEditChartProperties();
        }
    }

}