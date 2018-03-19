package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class BarChartDemo extends ApplicationFrame {

    public BarChartDemo(String appTitle, String charTitle) {
        super(appTitle);
        JFreeChart barChart = ChartFactory.createBarChart(charTitle, "Category", "Score", createDataset(), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(barChart);
        panel.setPreferredSize(new Dimension(560, 367));
        setContentPane(panel);
    }

    public static CategoryDataset createDataset() {
        String fiat = "FIAT";
        String audi = "AUDI";
        String ford = "FORD";
        String speed = "Speed";
        String millage = "Millage";
        String userrating = "User Rating";
        String safety = "safety";

        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        dataset.addValue(1.0, fiat, speed);
        dataset.addValue(3.0, fiat, userrating);
        dataset.addValue(5.0, fiat, millage);
        dataset.addValue(5.0, fiat, safety);

        dataset.addValue(5.0, audi, speed);
        dataset.addValue(6.0, audi, userrating);
        dataset.addValue(10.0, audi, millage);
        dataset.addValue(4.0, audi, safety);

        dataset.addValue(4.0, ford, speed);
        dataset.addValue(2.0, ford, userrating);
        dataset.addValue(3.0, ford, millage);
        dataset.addValue(6.0, ford, safety);

        return dataset;
    }

    public static void main(String[] args) throws IOException {
        BarChartDemo demo = new BarChartDemo("Car Usage Statistics", "Which car do you like?");
//      располагаем график в окне
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
//        для версии 1.5
//        ChartUtils.saveChartAsJPEG(new File("barChart.jpeg"), ChartFactory.createBarChart("Which car do you like?", "Category", "Score", createDataset(), PlotOrientation.VERTICAL, true, true, false), 640, 480);
        ChartUtilities.saveChartAsJPEG(new File("barChart.jpeg"), ChartFactory.createBarChart("Which car do you like?", "Category", "Score", createDataset(), PlotOrientation.VERTICAL, true, true, false), 640, 480);
    }

}
