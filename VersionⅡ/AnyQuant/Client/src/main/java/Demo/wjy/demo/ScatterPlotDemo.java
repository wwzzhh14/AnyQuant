package Demo.wjy.demo;

/**
 * Created by Jiayiwu on 16/4/2.
 */
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import ui.panels.RecommendLoadingUI;

/**
 * A demo scatter plot.
 */
public class ScatterPlotDemo extends ApplicationFrame {

    /**
     * A demonstration application showing a scatter plot.
     *
     * @param title  the frame title.
     */
    public ScatterPlotDemo(String title) {
        super(title);
        JPanel chartPanel = new RecommendLoadingUI();
        chartPanel.setSize(1024,600);
//        chartPanel.setPreferredSize(new java.awt.Dimension(1024, 600));
        chartPanel.setVisible(true);

        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 1",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, false, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);


        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setUseOutlinePaint(true);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setTickMarkInsideLength(2.0f);
        domainAxis.setTickMarkOutsideLength(0.0f);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickMarkInsideLength(2.0f);
        rangeAxis.setTickMarkOutsideLength(0.0f);

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        // popup menu conflicts with axis trace
//        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        ScatterPlotDemo demo = new ScatterPlotDemo("test");
        demo.add(new IncreamentDemo().getLineAndHistogramStockPanel("2016-02-02","2016-03-03","sh601288","hs300"));
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
//        StockInfoBLImpl a = new StockInfoBLImpl();
//       List<IncreasementVO>c= a.getStockInscreasment("2016-03-03","2016-03-05","sh601288");
//        System.out.println(c.get(0).getDate());
    }


    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Random 1");
        series1.add(new Integer(1), new Double(500.2));
        series1.add(new Integer(2), new Double(694.1));
        series1.add(new Integer(3), new Double(-734.4));
        series1.add(new Integer(4), new Double(453.2));
        series1.add(new Integer(5), new Double(500.2));
        series1.add(new Integer(6), new Double(300.7));
        series1.add(new Integer(7), new Double(734.4));
        series1.add(new Integer(8), new Double(453.2));

        XYSeries series2 = new XYSeries("Random 2");
        series2.add(new Integer(1), new Double(700.2));
        series2.add(new Integer(2), new Double(534.1));
        series2.add(new Integer(3), new Double(323.4));
        series2.add(new Integer(4), new Double(125.2));
        series2.add(new Integer(5), new Double(653.2));
        series2.add(new Integer(6), new Double(432.7));
        series2.add(new Integer(7), new Double(564.4));
        series2.add(new Integer(8), new Double(322.2));

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth(0.0);
        return dataset;
    }
}
