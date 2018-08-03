package jfreechart;

import javafx.animation.Timeline;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

//import org.jfree.chart.ChartUtils;

public class XYChart extends ApplicationFrame {

    public XYChart(String appTitle, String chartTitle) {

        super(appTitle);
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, "Category", "Score", createDataset(), PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(560, 367));
        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
//      отображаем на оси только целые числа
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        domainAxis.setTickMarksVisible(false);
        plot.getRangeAxis().setTickMarksVisible(false);
        Timeline timeline = new Timeline();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // отключает выделение точек фигурой
        renderer.setBaseShapesVisible(true);
        // отключает соединения линиями между точками
        renderer.setBaseLinesVisible(false);
//      устанавливаем цвет для нескольких видов данных(серий)
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(panel);
    }

    public static XYDataset createDataset() {
//      описываем каждый вид серий
        XYSeries firefox = new XYSeries("Firefox");
        firefox.add(1.0, 1.0);
        firefox.add(2.0, 4.0);
        firefox.add(3.0, 3.0);

        XYSeries chrome = new XYSeries("Chrome");
        chrome.add(1.0, 4.0);
        chrome.add(2.0, 5.0);
        chrome.add(3.0, 6.0);

        XYSeries iexplorer = new XYSeries("InternetExplorer");
        iexplorer.add(3.0, 4.0);
        iexplorer.add(4.0, 5.0);
        iexplorer.add(5.0, 4.0);

        XYSeriesCollection dataset = new XYSeriesCollection();
//      добавляем серии(они сопоставляются с индексами начиная с нуля)
        dataset.addSeries(firefox);
        dataset.addSeries(chrome);
        dataset.addSeries(iexplorer);
        return dataset;
    }

    public static void main(String[] args) throws IOException {
        XYChart chart = new XYChart("Browser Usage Statistics",
                "Which Browser are you using?");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
//      для версии 1.5
//        ChartUtils.saveChartAsJPEG(new File("XYChart.jpeg"), ChartFactory.createXYLineChart("Which Browser are you using?", "Category", "Score", createDataset(), PlotOrientation.VERTICAL, true, true, false), 670, 480);
        ChartUtilities.saveChartAsJPEG(new File("XYChart.jpeg"), ChartFactory.createXYLineChart("Which Browser are you using?", "Category", "Score", createDataset(), PlotOrientation.VERTICAL, true, true, false), 670, 480);
    }
}
