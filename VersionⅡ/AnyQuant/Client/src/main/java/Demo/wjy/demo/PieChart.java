package Demo.wjy.demo;

import org.knowm.xchart.ChartBuilder_Pie;

import org.knowm.xchart.Chart_Pie;
import org.knowm.xchart.SwingWrapper;
import Demo.ui.ExampleChart;
import org.knowm.xchart.internal.chartpart.Chart;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell on 2016/3/15.
 */
public class PieChart implements ExampleChart{
    Chart_Pie chart;
    public PieChart(List<Double> data){
        chart= new ChartBuilder_Pie().width(800).height(600).title(getClass().getSimpleName()).build();
        List<Double> Data = data;
        int size=Data.size();
        for(int i=0;i<size;i++){
            Color color = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
            chart.addSeries(color.toString(), Data.get(i));
        }

    }
   public Chart getChart(){
       return chart;
   }
    public static void main(String[] args) {
        List<Double> Data = new ArrayList<Double>();
        Data.add(24.0);
        Data.add(355.0);
        ExampleChart exampleChart = new PieChart(Data);
        Chart chart = exampleChart.getChart();
        new SwingWrapper(chart).displayChart();
    }




}
