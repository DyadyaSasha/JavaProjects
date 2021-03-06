package jfreechartexamples;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.RectangleEdge;

/**
 * A demo showing crosshairs that follow the data points on an XYPlot.
 */
public class CrosshairOverlayDemo1 extends JFrame {

    static class MyDemoPanel extends JPanel implements ChartMouseListener {

        private ChartPanel chartPanel;

        private Crosshair xCrosshair;

        private Crosshair yCrosshair;

        public MyDemoPanel() {
            super(new BorderLayout());
            JFreeChart chart = createChart(createDataset());
            this.chartPanel = new ChartPanel(chart);
            this.chartPanel.addChartMouseListener(this);
            CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
            this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY,
                    new BasicStroke(0f));
            this.xCrosshair.setLabelVisible(true);
            this.yCrosshair = new Crosshair(Double.NaN, Color.GRAY,
                    new BasicStroke(0f));
            this.yCrosshair.setLabelVisible(true);
            crosshairOverlay.addDomainCrosshair(xCrosshair);
            crosshairOverlay.addRangeCrosshair(yCrosshair);
            this.chartPanel.addOverlay(crosshairOverlay);
            add(this.chartPanel);
        }

        private JFreeChart createChart(XYDataset dataset) {
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "CrosshairOverlayDemo1", "X", "Y", dataset);
            return chart;
        }

        private XYDataset createDataset() {
            XYSeries series = new XYSeries("S1");
            // при  1_000_000 - заметно незначительное отставание от курсора при движении креста
            // с 300_000 и ниже - отставание креста от курсора незаметно
            for (int x = 0; x < 100_000; x++) {
                series.add(x, 0 + Math.random() * (100 - 0 + 1));
            }
            XYSeriesCollection dataset = new XYSeriesCollection(series);
            return dataset;
        }

        @Override
        public void chartMouseClicked(ChartMouseEvent event) {
            // ignore
        }

        @Override
        public void chartMouseMoved(ChartMouseEvent event) {
            Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
            JFreeChart chart = event.getChart();
            XYPlot plot = (XYPlot) chart.getPlot();
            ValueAxis xAxis = plot.getDomainAxis();
            double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea,
                    RectangleEdge.BOTTOM);
            // make the crosshairs disappear if the mouse is out of range
            if (!xAxis.getRange().contains(x)) {
                x = Double.NaN;
            }
            double y = plot.getRangeAxis().java2DToValue(event.getTrigger().getY(), dataArea , RectangleEdge.RIGHT);
//            double y = DatasetUtils.findYValue(plot.getDataset(), 0, x);
            this.xCrosshair.setValue(x);
            this.yCrosshair.setValue(y);
        }

    }

    public CrosshairOverlayDemo1(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CrosshairOverlayDemo1 app = new CrosshairOverlayDemo1(
                        "JFreeChart: CrosshairOverlayDemo1.java");
                app.pack();
                app.setVisible(true);
            }
        });
    }

}
