package jfreechart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

public class CombinedChart extends ApplicationFrame {


    public CombinedChart(String title) {
        super(title);
        JFreeChart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }


    private JFreeChart createChart() {

        XYDataset data1 = new TimeSeriesCollection();
        XYDataset data2 = new TimeSeriesCollection();
        XYDataset data3 = new TimeSeriesCollection();

        XYPlot subRangePlot1 = new XYPlot(data1, null, null, new XYLineAndShapeRenderer());
        XYPlot subRangePlot2 = new XYPlot(data2,  null, null, new XYLineAndShapeRenderer());
        XYPlot subDomainPlot3 = new XYPlot(data3, null, new NumberAxis("asdasd"), new XYLineAndShapeRenderer());


        NumberAxis domainAxis = new NumberAxis("domain");
        CombinedDomainXYPlot domainPlot = new CombinedDomainXYPlot(domainAxis);

        CombinedRangeXYPlot rangePlot = new CombinedRangeXYPlot(new NumberAxis("range"));

        rangePlot.add(subRangePlot1, 6);
        rangePlot.add(subRangePlot2, 2);

        JFreeChart chart = new JFreeChart(domainPlot);

        subRangePlot2.setBackgroundPaint(chart.getBackgroundPaint());

        domainPlot.add(rangePlot);
        domainPlot.add(subDomainPlot3);

        return chart;
    }

    public static void main(String[] args) {
        CombinedChart chart = new CombinedChart("Combined Chart");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}