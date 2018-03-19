package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BarChart3DDemo {

    public static void main(String[] args) throws IOException {

        String fait = "FAIT";
        String audi = "AUDI";
        String ford = "FORD";
        String speed = "Speed";
        String popular = "Popular";
        String mailage = "Mailage";
        String userrating = "User Rating";
        String safety = "safety";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, fait, speed);
        dataset.addValue(4.0, fait, popular);
        dataset.addValue(3.0, fait, userrating);
        dataset.addValue(5.0, fait, mailage);
        dataset.addValue(5.0, fait, safety);

        dataset.addValue(5.0, audi, speed);
        dataset.addValue(7.0, audi, popular);
        dataset.addValue(6.0, audi, userrating);
        dataset.addValue(10.0, audi, mailage);
        dataset.addValue(4.0, audi, safety);

        dataset.addValue(4.0, ford, speed);
        dataset.addValue(3.0, ford, popular);
        dataset.addValue(2.0, ford, userrating);
        dataset.addValue(3.0, ford, mailage);
        dataset.addValue(6.0, ford, safety);

        JFreeChart chart = ChartFactory.createBarChart3D(
                "Car Usage Statistics",
                "Category",
                "Score",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartUtilities.saveChartAsJPEG(new File("BarChart3D.jpeg"), chart, 600, 480);

        ApplicationFrame frame = new ApplicationFrame("BarChart3D");
        ChartPanel panel = new ChartPanel(chart);
        frame.setContentPane(panel);
        frame.setSize(new Dimension(560,400));
        frame.setVisible(true);



    }
}
