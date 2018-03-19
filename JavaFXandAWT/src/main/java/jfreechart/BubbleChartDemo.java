package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BubbleChartDemo extends ApplicationFrame{

    public BubbleChartDemo(String title){
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new Dimension(560, 370));
        setContentPane(panel);
    }

    private static JPanel createDemoPanel(){
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(true);
        return panel;
    }

    private static JFreeChart createChart(XYZDataset dataset){
        JFreeChart chart = ChartFactory.createBubbleChart(
                "AGE vs WEIGHT vs WORK",
                "Weight",
                "AGE",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        XYPlot plot = chart.getXYPlot();
        plot.setForegroundAlpha(0.65f);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        NumberAxis numberAxis = (NumberAxis) plot.getDomainAxis();
        numberAxis.setLowerMargin(0.2);
        numberAxis.setUpperMargin(0.5);
        NumberAxis numberAxis1 = (NumberAxis) plot.getRangeAxis();
        numberAxis1.setLowerMargin(0.8);
        numberAxis1.setUpperMargin(0.9);

        return chart;
    }

    private static XYZDataset createDataset(){
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double ad[] = {30, 40, 50, 60, 70, 80};
        double ad1[] = {10, 20, 30, 40, 50, 60};
        double ad2[] = {4, 5, 10, 8, 9, 6};
        double ad3[][] = {ad, ad1, ad2};
        dataset.addSeries("Series 1", ad3);
        return dataset;
    }


    public static void main(String[] args) throws IOException {
        BubbleChartDemo demo = new BubbleChartDemo("Bubble Chart");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        ChartUtilities.saveChartAsJPEG(new File("BubbleChart.jpeg"), createChart(createDataset()), 640, 480);
    }
}
