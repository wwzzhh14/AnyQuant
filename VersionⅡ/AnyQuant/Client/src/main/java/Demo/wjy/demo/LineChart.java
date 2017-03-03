package Demo.wjy.demo;

import Demo.ui.ExampleChart;

import org.knowm.xchart.ChartBuilder_XY;
import org.knowm.xchart.Chart_XY;
import org.knowm.xchart.Series_XY;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.style.Styler;
import org.knowm.xchart.internal.style.colors.ChartColor;
import org.knowm.xchart.internal.style.colors.XChartSeriesColors;
import org.knowm.xchart.internal.style.lines.SeriesLines;
import org.knowm.xchart.internal.style.markers.SeriesMarkers;


import java.awt.*;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dell on 2016/3/15.
 */
public class LineChart implements ExampleChart{

    Chart_XY chart;

    public LineChart(List<Date> xdata,List<Double>  ydata){
        List<Date> xData = xdata;
        List<Double> yData = ydata;
        chart= new ChartBuilder_XY().width(800).height(600).title("AnyQuant").xAxisTitle("Data").yAxisTitle("成交量").build();
        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.GREY));
        chart.getStyler().setPlotGridLinesColor(new Color(255, 255, 255));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendBackgroundColor(Color.PINK);
        chart.getStyler().setChartFontColor(Color.MAGENTA);
        chart.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart.getStyler().setChartTitleBoxVisible(true);
        chart.getStyler().setChartTitleBoxBorderColor(Color.BLACK);
        chart.getStyler().setPlotGridLinesVisible(false);

        chart.getStyler().setAxisTickPadding(20);

        chart.getStyler().setAxisTickMarkLength(15);

        chart.getStyler().setPlotMargin(20);

        chart.getStyler().setChartTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 18));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setLegendSeriesLineLength(12);
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 11));
        chart.getStyler().setDatePattern("dd-MMM");
        chart.getStyler().setDecimalPattern("#0.000");
        chart.getStyler().setLocale(Locale.GERMAN);

        // Series
        Series_XY series = chart.addSeries("Data", xData, yData);
        series.setLineColor(XChartSeriesColors.BLUE);
        series.setMarkerColor(Color.ORANGE);
        series.setMarker(SeriesMarkers.CIRCLE);
        series.setLineStyle(SeriesLines.SOLID);

    }
public Chart getChart(){
    return chart;
}
    public static void main(String[] args) {
        List<Date> xData = new ArrayList<Date>();
        List<Double> yData =new ArrayList<Double>();
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        for (int i = 1; i <= 30; i++) {

            try {
                date = sdf.parse(i + ".3.2016");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xData.add(date);
            yData.add(Math.random() * i);
        }

        ExampleChart exampleChart = new LineChart(xData,yData);
        Chart chart = exampleChart.getChart();
        new SwingWrapper(chart).displayChart();

    }


}
