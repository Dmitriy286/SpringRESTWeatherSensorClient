package org.weathersensor.SpringRESTClient.chart;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Chart {

    public void createChart() {
        double[] xData = new double[] { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
        double[] yData = new double[] { 2.0, 1.0, 0.0, 3.0, 7.0, 5.0, 3.0, 2.0, 7.0, 8.0, 9.0 };

// Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

// Show it
        new SwingWrapper(chart).displayChart();

// Save it
        try {
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
