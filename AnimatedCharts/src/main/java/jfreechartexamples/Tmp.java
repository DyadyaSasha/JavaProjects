package jfreechartexamples;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author ovilia
 */
public class Tmp {

    private static JFreeChart createChart(XYDataset paramXYDataset) {
        JFreeChart localJFreeChart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", paramXYDataset, true, true, false);
        XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();
        localXYPlot.setDomainPannable(true);
        localXYPlot.setRangePannable(false);
        localXYPlot.setDomainCrosshairVisible(true);
        localXYPlot.setRangeCrosshairVisible(true);
        XYItemRenderer localXYItemRenderer = localXYPlot.getRenderer();
        if ((localXYItemRenderer instanceof XYLineAndShapeRenderer)) {
            Object localObject = (XYLineAndShapeRenderer) localXYItemRenderer;
            ((XYLineAndShapeRenderer) localObject).setDefaultShapesVisible(false);
        }
        Object localObject = (DateAxis) localXYPlot.getDomainAxis();
        ((DateAxis) localObject).setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return (JFreeChart) localJFreeChart;
    }

    private static XYDataset createDataset() {
        TimeSeries localTimeSeries1 = new TimeSeries("L&G European Index Trust");
        localTimeSeries1.add(new Month(2, 2001), 181.80000000000001D);
        localTimeSeries1.add(new Month(3, 2001), 167.30000000000001D);
        localTimeSeries1.add(new Month(4, 2001), 153.80000000000001D);
        localTimeSeries1.add(new Month(5, 2001), 167.59999999999999D);
        localTimeSeries1.add(new Month(6, 2001), 158.80000000000001D);
        localTimeSeries1.add(new Month(7, 2001), 148.30000000000001D);
        localTimeSeries1.add(new Month(8, 2001), 153.90000000000001D);
        localTimeSeries1.add(new Month(9, 2001), 142.69999999999999D);
        localTimeSeries1.add(new Month(10, 2001), 123.2D);
        localTimeSeries1.add(new Month(11, 2001), 131.80000000000001D);
        localTimeSeries1.add(new Month(12, 2001), 139.59999999999999D);
        localTimeSeries1.add(new Month(1, 2002), 142.90000000000001D);
        localTimeSeries1.add(new Month(2, 2002), 138.69999999999999D);
        localTimeSeries1.add(new Month(3, 2002), 137.30000000000001D);
        localTimeSeries1.add(new Month(4, 2002), 143.90000000000001D);
        localTimeSeries1.add(new Month(5, 2002), 139.80000000000001D);
        localTimeSeries1.add(new Month(6, 2002), 137.0D);
        localTimeSeries1.add(new Month(7, 2002), 132.80000000000001D);
        localTimeSeries1.add(new Month(8, 2002), 110.3D);
        localTimeSeries1.add(new Month(9, 2002), 110.5D);
        localTimeSeries1.add(new Month(10, 2002), 94.109999999999999D);
        localTimeSeries1.add(new Month(11, 2002), 102.5D);
        localTimeSeries1.add(new Month(12, 2002), 112.3D);
        localTimeSeries1.add(new Month(1, 2003), 104.0D);
        localTimeSeries1.add(new Month(2, 2003), 98.530000000000001D);
        localTimeSeries1.add(new Month(3, 2003), 97.150000000000006D);
        localTimeSeries1.add(new Month(4, 2003), 94.900000000000006D);
        localTimeSeries1.add(new Month(5, 2003), 107.8D);
        localTimeSeries1.add(new Month(6, 2003), 113.7D);
        localTimeSeries1.add(new Month(7, 2003), 112.5D);
        localTimeSeries1.add(new Month(8, 2003), 118.59999999999999D);
        localTimeSeries1.add(new Month(9, 2003), 123.8D);
        localTimeSeries1.add(new Month(10, 2003), 117.2D);
        localTimeSeries1.add(new Month(11, 2003), 123.0D);
        localTimeSeries1.add(new Month(12, 2003), 127.0D);
        localTimeSeries1.add(new Month(1, 2004), 132.69999999999999D);
        localTimeSeries1.add(new Month(2, 2004), 132.40000000000001D);
        localTimeSeries1.add(new Month(3, 2004), 131.69999999999999D);
        localTimeSeries1.add(new Month(4, 2004), 128.0D);
        localTimeSeries1.add(new Month(5, 2004), 131.80000000000001D);
        localTimeSeries1.add(new Month(6, 2004), 127.40000000000001D);
        localTimeSeries1.add(new Month(7, 2004), 133.5D);
        localTimeSeries1.add(new Month(8, 2004), 126.0D);
        localTimeSeries1.add(new Month(9, 2004), 129.5D);
        localTimeSeries1.add(new Month(10, 2004), 135.30000000000001D);
        localTimeSeries1.add(new Month(11, 2004), 138.0D);
        localTimeSeries1.add(new Month(12, 2004), 141.30000000000001D);
        localTimeSeries1.add(new Month(1, 2005), 148.80000000000001D);
        localTimeSeries1.add(new Month(2, 2005), 147.09999999999999D);
        localTimeSeries1.add(new Month(3, 2005), 150.69999999999999D);
        localTimeSeries1.add(new Month(4, 2005), 150.0D);
        localTimeSeries1.add(new Month(5, 2005), 145.69999999999999D);
        localTimeSeries1.add(new Month(6, 2005), 152.0D);
        localTimeSeries1.add(new Month(7, 2005), 157.19999999999999D);
        localTimeSeries1.add(new Month(8, 2005), 167.0D);
        localTimeSeries1.add(new Month(9, 2005), 165.0D);
        localTimeSeries1.add(new Month(10, 2005), 171.59999999999999D);
        localTimeSeries1.add(new Month(11, 2005), 166.19999999999999D);
        localTimeSeries1.add(new Month(12, 2005), 174.30000000000001D);
        localTimeSeries1.add(new Month(1, 2006), 183.80000000000001D);
        localTimeSeries1.add(new Month(2, 2006), 187.0D);
        localTimeSeries1.add(new Month(3, 2006), 191.30000000000001D);
        localTimeSeries1.add(new Month(4, 2006), 202.5D);
        localTimeSeries1.add(new Month(5, 2006), 200.59999999999999D);
        localTimeSeries1.add(new Month(6, 2006), 187.30000000000001D);
        localTimeSeries1.add(new Month(7, 2006), 192.19999999999999D);
        localTimeSeries1.add(new Month(8, 2006), 190.80000000000001D);
        localTimeSeries1.add(new Month(9, 2006), 194.69999999999999D);
        localTimeSeries1.add(new Month(10, 2006), 201.30000000000001D);
        localTimeSeries1.add(new Month(11, 2006), 205.09999999999999D);
        localTimeSeries1.add(new Month(12, 2006), 206.69999999999999D);
        localTimeSeries1.add(new Month(1, 2007), 216.80000000000001D);
        localTimeSeries1.add(new Month(2, 2007), 218.0D);
        localTimeSeries1.add(new Month(3, 2007), 215.40000000000001D);
        localTimeSeries1.add(new Month(4, 2007), 223.0D);
        localTimeSeries1.add(new Month(5, 2007), 235.09999999999999D);
        localTimeSeries1.add(new Month(6, 2007), 242.0D);
        localTimeSeries1.add(new Month(7, 2007), 237.80000000000001D);
        TimeSeries localTimeSeries2 = new TimeSeries("L&G UK Index Trust");
        localTimeSeries2.add(new Month(2, 2001), 129.59999999999999D);
        localTimeSeries2.add(new Month(3, 2001), 123.2D);
        localTimeSeries2.add(new Month(4, 2001), 117.2D);
        localTimeSeries2.add(new Month(5, 2001), 124.09999999999999D);
        localTimeSeries2.add(new Month(6, 2001), 122.59999999999999D);
        localTimeSeries2.add(new Month(7, 2001), 119.2D);
        localTimeSeries2.add(new Month(8, 2001), 116.5D);
        localTimeSeries2.add(new Month(9, 2001), 112.7D);
        localTimeSeries2.add(new Month(10, 2001), 101.5D);
        localTimeSeries2.add(new Month(11, 2001), 106.09999999999999D);
        localTimeSeries2.add(new Month(12, 2001), 110.3D);
        localTimeSeries2.add(new Month(1, 2002), 111.7D);
        localTimeSeries2.add(new Month(2, 2002), 111.0D);
        localTimeSeries2.add(new Month(3, 2002), 109.59999999999999D);
        localTimeSeries2.add(new Month(4, 2002), 113.2D);
        localTimeSeries2.add(new Month(5, 2002), 111.59999999999999D);
        localTimeSeries2.add(new Month(6, 2002), 108.8D);
        localTimeSeries2.add(new Month(7, 2002), 101.59999999999999D);
        localTimeSeries2.add(new Month(8, 2002), 90.950000000000003D);
        localTimeSeries2.add(new Month(9, 2002), 91.019999999999996D);
        localTimeSeries2.add(new Month(10, 2002), 82.370000000000005D);
        localTimeSeries2.add(new Month(11, 2002), 86.319999999999993D);
        localTimeSeries2.add(new Month(12, 2002), 91.0D);
        localTimeSeries2.add(new Month(1, 2003), 86.0D);
        localTimeSeries2.add(new Month(2, 2003), 80.040000000000006D);
        localTimeSeries2.add(new Month(3, 2003), 80.400000000000006D);
        localTimeSeries2.add(new Month(4, 2003), 80.280000000000001D);
        localTimeSeries2.add(new Month(5, 2003), 86.420000000000002D);
        localTimeSeries2.add(new Month(6, 2003), 91.400000000000006D);
        localTimeSeries2.add(new Month(7, 2003), 90.519999999999996D);
        localTimeSeries2.add(new Month(8, 2003), 93.109999999999999D);
        localTimeSeries2.add(new Month(9, 2003), 96.799999999999997D);
        localTimeSeries2.add(new Month(10, 2003), 94.780000000000001D);
        localTimeSeries2.add(new Month(11, 2003), 99.560000000000002D);
        localTimeSeries2.add(new Month(12, 2003), 100.8D);
        localTimeSeries2.add(new Month(1, 2004), 103.40000000000001D);
        localTimeSeries2.add(new Month(2, 2004), 102.09999999999999D);
        localTimeSeries2.add(new Month(3, 2004), 105.3D);
        localTimeSeries2.add(new Month(4, 2004), 103.7D);
        localTimeSeries2.add(new Month(5, 2004), 105.2D);
        localTimeSeries2.add(new Month(6, 2004), 103.7D);
        localTimeSeries2.add(new Month(7, 2004), 105.7D);
        localTimeSeries2.add(new Month(8, 2004), 103.59999999999999D);
        localTimeSeries2.add(new Month(9, 2004), 106.09999999999999D);
        localTimeSeries2.add(new Month(10, 2004), 109.3D);
        localTimeSeries2.add(new Month(11, 2004), 110.3D);
        localTimeSeries2.add(new Month(12, 2004), 112.59999999999999D);
        localTimeSeries2.add(new Month(1, 2005), 116.0D);
        localTimeSeries2.add(new Month(2, 2005), 117.3D);
        localTimeSeries2.add(new Month(3, 2005), 120.09999999999999D);
        localTimeSeries2.add(new Month(4, 2005), 119.3D);
        localTimeSeries2.add(new Month(5, 2005), 116.2D);
        localTimeSeries2.add(new Month(6, 2005), 120.8D);
        localTimeSeries2.add(new Month(7, 2005), 125.2D);
        localTimeSeries2.add(new Month(8, 2005), 127.7D);
        localTimeSeries2.add(new Month(9, 2005), 130.80000000000001D);
        localTimeSeries2.add(new Month(10, 2005), 131.0D);
        localTimeSeries2.add(new Month(11, 2005), 135.30000000000001D);
        localTimeSeries2.add(new Month(12, 2005), 141.19999999999999D);
        localTimeSeries2.add(new Month(1, 2006), 144.69999999999999D);
        localTimeSeries2.add(new Month(2, 2006), 146.40000000000001D);
        localTimeSeries2.add(new Month(3, 2006), 151.90000000000001D);
        localTimeSeries2.add(new Month(4, 2006), 153.5D);
        localTimeSeries2.add(new Month(5, 2006), 144.5D);
        localTimeSeries2.add(new Month(6, 2006), 150.09999999999999D);
        localTimeSeries2.add(new Month(7, 2006), 148.69999999999999D);
        localTimeSeries2.add(new Month(8, 2006), 150.09999999999999D);
        localTimeSeries2.add(new Month(9, 2006), 151.59999999999999D);
        localTimeSeries2.add(new Month(10, 2006), 153.40000000000001D);
        localTimeSeries2.add(new Month(11, 2006), 158.30000000000001D);
        localTimeSeries2.add(new Month(12, 2006), 157.59999999999999D);
        localTimeSeries2.add(new Month(1, 2007), 163.90000000000001D);
        localTimeSeries2.add(new Month(2, 2007), 163.80000000000001D);
        localTimeSeries2.add(new Month(3, 2007), 162.0D);
        localTimeSeries2.add(new Month(4, 2007), 167.09999999999999D);
        localTimeSeries2.add(new Month(5, 2007), 170.0D);
        localTimeSeries2.add(new Month(6, 2007), 175.69999999999999D);
        localTimeSeries2.add(new Month(7, 2007), 171.90000000000001D);
        TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection();
        localTimeSeriesCollection.addSeries(localTimeSeries1);
        localTimeSeriesCollection.addSeries(localTimeSeries2);
        return localTimeSeriesCollection;
    }

    public static JPanel createDemoPanel() {
        JFreeChart localJFreeChart = createChart(createDataset());
        XYItemRenderer render = new XYAreaRenderer();
        XYPlot plot = localJFreeChart.getXYPlot();
        plot.setRenderer(render);
        return new ChartPanel(localJFreeChart);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JPanel localJPanel = createDemoPanel();
        localJPanel.setPreferredSize(new Dimension(500, 270));
        JFrame frame = new JFrame("Demo");
        frame.add(localJPanel);
        frame.show();
    }
}