package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
//import org.jfree.chart.ChartUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PieChart3DDemo {

    public static void main(String[] args) throws IOException {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("IPhone 5s" , new Double(20));
        dataset.setValue("SamSung Grand" , new Double(20));
        dataset.setValue("MotoG" , new Double(40));
        dataset.setValue("Nokia Lumia" , new Double(10));

        JFreeChart chart = ChartFactory.createPieChart3D(
                "Mobile Sales",
                dataset,
                true,
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
//      устанавливаем прозрачность
        plot.setForegroundAlpha(0.2f);
        plot.setInteriorGap(0.02);
//      для версии 1.5
//        ChartUtils.saveChartAsJPEG(new File("PieChart3D.jpeg"), chart, 560, 400);
        ChartUtilities.saveChartAsJPEG(new File("PieChart3D.jpeg"), chart, 560, 400);
        ApplicationFrame frame = new ApplicationFrame("PieChart3D");
        ChartPanel panel = new ChartPanel(chart);
        frame.setContentPane(panel);
        frame.setSize(new Dimension(560, 400));
        frame.setVisible(true);

    }

}
