package businesslogic.visualizationlogic;

import businesslogic.MarketBlLImpl;
import businesslogicservice.MarketBLService;
import businesslogicservice.visualizationlogicservice.VisualizationRecommendPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import vo.QuadrantDiagramVO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jiayiwu on 16/4/2.
 */
public class VisualizationRecommendPanelimpl implements VisualizationRecommendPanel {




    public JPanel getScatterPanel() {
        Calendar c=Calendar.getInstance();
        long date=c.getTimeInMillis();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(date);
        String startTime=sdf.format(c.getTime());
        MarketBLService databl = new MarketBlLImpl();
        XYSeriesCollection dataset = new XYSeriesCollection();
        List<QuadrantDiagramVO> data = databl.getQuadrantDiagramInfo(startTime,15);


        for (int i = 0;i<data.size();i++){
            QuadrantDiagramVO vo = data.get(i);
            XYSeries xy = new XYSeries(vo.getName());
            xy.add(vo.getPe(),vo.getSum());
            dataset.addSeries(xy);
        }


        JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 1",
                "PE", "Volume", dataset, PlotOrientation.VERTICAL, true, false, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);

        plot.setBackgroundPaint(Color.black);
        plot.setDomainCrosshairPaint(Color.red);
        plot.setRangeCrosshairPaint(Color.red);
        plot.setRangeZeroBaselinePaint(Color.red);
        plot.setDomainZeroBaselinePaint(Color.red);
        plot.setRangeGridlinePaint(Color.black);
        plot.setDomainGridlinePaint(Color.black);


        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setUseOutlinePaint(true);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAxisLinePaint(Color.red);
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setTickMarkInsideLength(2.0f);
        domainAxis.setTickMarkOutsideLength(0.0f);
        domainAxis.setTickLabelPaint(Color.white);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickMarkInsideLength(2.0f);
        rangeAxis.setTickMarkOutsideLength(0.0f);
        rangeAxis.setTickLabelPaint(Color.white);
        rangeAxis.setAxisLinePaint(Color.red);

        chart.setBackgroundPaint(Color.black);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.BLACK);
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
//        chartPanel.setZoomFillPaint(Color.white);
//        chart.setBorderPaint(Color.white);

        return chartPanel;
    }
}
