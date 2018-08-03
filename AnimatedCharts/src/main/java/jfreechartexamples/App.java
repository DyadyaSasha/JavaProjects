package jfreechartexamples;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Charts");

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                XYDataset ds = createDataset();
                JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
                        "x", "y", ds, PlotOrientation.VERTICAL, true, true,
                        false);

                ChartPanel cp = new ChartPanel(chart);

                frame.getContentPane().add(cp);
            }
        });

    }

    private static XYDataset createDataset() {

        XYSeries series = new XYSeries("S1");
        // при  1_000_000 - заметно незначительное отставание от курсора при движении креста
        // с 300_000 и ниже - отставание креста от курсора незаметно
        for (int x = 0; x < 300_000; x++) {
            series.add(x, 0 + Math.random() * (100 - 0 + 1));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

}
