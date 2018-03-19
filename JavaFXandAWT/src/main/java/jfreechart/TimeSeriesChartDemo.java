package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TimeSeriesChartDemo extends ApplicationFrame {

    public TimeSeriesChartDemo(String title){
        super(title);
        ChartPanel panel = new ChartPanel(createChart(createDataset()));
        panel.setPreferredSize(new Dimension(560, 370));
        panel.setMouseZoomable(true, false);
        setContentPane(panel);
    }

    private static XYDataset createDataset(){
        TimeSeries series = new TimeSeries("Random Data");
        Second second = new Second();
        double val = 100.0;
        for(int i = 0; i < 4000; i++){
            try {
                val += Math.random() - 0.5;
                series.add(second, val);
                second = (Second) second.next();
            } catch (SeriesException e){
                e.printStackTrace();
            }

        }

        return new TimeSeriesCollection(series);
    }

    private static JFreeChart createChart(XYDataset dataset){
        return ChartFactory.createTimeSeriesChart(
                "Computing Test",
                "Seconds",
                "Value",
                dataset,
                false,
                false,
                false
        );
    }

    public static void main(String[] args) throws IOException {
        TimeSeriesChartDemo demo = new TimeSeriesChartDemo("Time Series Management");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        ChartUtilities.saveChartAsJPEG(new File("TimeSeriesChart.jpeg"), createChart(createDataset()), 640, 480);
    }
}
