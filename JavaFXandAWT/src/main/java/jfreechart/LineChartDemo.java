package jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LineChartDemo extends ApplicationFrame{

    public LineChartDemo(String appTitle, String charTitle){
        super(appTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(charTitle, "Years","Number of Schools", createDataset(),
        PlotOrientation.VERTICAL,
        true, true, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        setContentPane(chartPanel);
    }

    public static DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue( 15 , "schools" , "1970" );
        dataset.addValue( 30 , "schools" , "1980" );
        dataset.addValue( 60 , "schools" ,  "1990" );
        dataset.addValue( 120 , "schools" , "2000" );
        dataset.addValue( 240 , "schools" , "2010" );
        dataset.addValue( 300 , "schools" , "2014" );
        return dataset;
    }

    public static void main(String[] args) throws IOException {
        LineChartDemo chart = new LineChartDemo("School Vs Years" ,
                "Number of Schools vs years");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
//      для версии 1.5
//        ChartUtils.saveChartAsJPEG(new File("linechart.jpeg"), ChartFactory.createLineChart("Number of Schools vs years", "Years","Number of Schools", createDataset(), PlotOrientation.VERTICAL, true, true, false), 640, 480);
        ChartUtilities.saveChartAsJPEG(new File("linechart.jpeg"), ChartFactory.createLineChart("Number of Schools vs years", "Years","Number of Schools", createDataset(), PlotOrientation.VERTICAL, true, true, false), 640, 480);
    }
}
