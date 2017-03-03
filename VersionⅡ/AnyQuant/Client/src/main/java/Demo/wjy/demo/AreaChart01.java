package Demo.wjy.demo;

/**
 * Created by Jiayiwu on 16/3/12.
 */


import Demo.ui.ExampleChart;
import org.knowm.xchart.ChartBuilder_XY;
import org.knowm.xchart.Chart_XY;
import org.knowm.xchart.Series_XY;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.style.Styler;


/**
 * Creates a simple Chart using QuickChart
 */
public class AreaChart01 implements ExampleChart {

    public static void main(String[] args) {

        ExampleChart exampleChart = new AreaChart01();
        Chart chart = exampleChart.getChart();
        new SwingWrapper(chart).displayChart();
    }


    public Chart getChart() {

        // Create Chart
        Chart_XY chart = new ChartBuilder_XY().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("X").yAxisTitle("Y").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAxisTitlesVisible(false);
        chart.getStyler().setDefaultSeriesRenderStyle(Series_XY.ChartXYSeriesRenderStyle.Area);

        // Series
        chart.addSeries("a", new double[] { 0, 3, 5, 7, 9 }, new double[] { -3, 5, 9, 6, 5 });
        chart.addSeries("b", new double[] { 0, 2, 4, 6, 9 }, new double[] { -1, 6, 4, 0, 4 });
        chart.addSeries("c", new double[] { 0, 1, 3, 8, 9 }, new double[] { -2, -1, 1, 0, 1 });

        return chart;
    }

}