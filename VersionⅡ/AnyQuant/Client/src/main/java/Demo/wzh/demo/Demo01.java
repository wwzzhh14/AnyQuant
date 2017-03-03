package Demo.wzh.demo;

import org.knowm.xchart.*;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.style.Styler;

import java.util.Arrays;

/**
 * Created by HP on 2016/3/14.
 */
public class Demo01 {
    public static Chart getChart(){
//        double[] array1={1,2,3};
//        double[] array2={1,2,3};
//        Chart chart= QuickChart.getChart("","x","y","y(x)",array1,array2);
//

//        Chart_XY chart = new ChartBuilder_XY().width(800).height(600).title("").xAxisTitle("X").yAxisTitle("Y").build();
        Chart_Category chart=new ChartBuilder_Category().width(600).height(400).title("aaa").xAxisTitle("X").yAxisTitle("Y").build();
;
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setPlotContentSize(1.0);
        chart.getStyler().setAvailableSpaceFill(0.1);
        chart.getStyler().setOverlapped(false);
//        chart.getStyler().setAxisTickPadding(100);

        chart.addSeries("test1", Arrays.asList(new Integer[]{0,1,2,3,4}),Arrays.asList(new Integer[]{100,101,102,103,101}), Arrays.asList(new Integer[]{0,1,2,3,4}));
        chart.addSeries("test2", Arrays.asList(new Integer[]{0,1,2,3,4}),Arrays.asList(new Integer[]{200,201,202,203,11}));

//        new SwingWrapper(chart).displayChart();
        return chart;

    }
}
