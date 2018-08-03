package app.view.charts;


import app.models.Data;
import app.view.AppConstants;
import app.view.jfreechart.TaChartCanvas;
import app.view.jfreechart.TaChartViewer;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepAreaRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.util.ShapeUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.text.NumberFormat;

public class JfreeStockLineChart extends StackPane {

    private CombinedRangeXYPlot plot;
    private UpdateChartsService updateService;
    private TaChartCanvas chartCanvas;
    private TaChartViewer chartViewer;
    private boolean firstCall = true;

    public JfreeStockLineChart() {

        ValueAxis yAxis = new NumberAxis();
        yAxis.setTickMarksVisible(false);
        yAxis.setAutoRange(false);
        yAxis.setUpperBound(60);
        yAxis.setLowerBound(0);
//      TODO: доделать 10 ячеек
//        ((NumberAxis)yAxis).setTickUnit();
        plot = new CombinedRangeXYPlot(yAxis);
        plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        plot.setGap(0);

        JFreeChart chart = new JFreeChart(null, null, plot, false);

//       chart.setBackgroundPaint(Color.WHITE);
        chartViewer = new TaChartViewer(chart);
        chartCanvas = chartViewer.getCanvas();

        XYPlot stockPlot = createStockChart();
        XYPlot cupChart = createMarketDepthChart();

        plot.add(stockPlot, 6);
        plot.add(cupChart, 2);


        getChildren().add(chartViewer);
        VBox.setVgrow(this, Priority.SOMETIMES);

        updateService = new UpdateChartsService();
        updateService.setPeriod(Duration.millis(AppConstants.REFRESH_PERIOD));

        updateService.setOnSucceeded(event -> {
            Data data = (Data) event.getSource().getValue();
            ((XYSeriesCollection) stockPlot.getDataset()).getSeries(0).add(data.getX(), data.getY());
            if (data.isAutoIncreasedBounds()) {
                ValueAxis domainStockAxis = ((XYPlot) plot.getSubplots().get(0)).getDomainAxis();
                domainStockAxis.setLowerBound(domainStockAxis.getLowerBound() + 1);
                domainStockAxis.setUpperBound(domainStockAxis.getUpperBound() + 1);
            }

            ImageView arrow = chartViewer.getArrow();

            if (data.isPanning()) {
                if (firstCall) {
                    arrow.layoutXProperty().bind(chartViewer.widthProperty().divide(8).multiply(6).subtract(50));
                    arrow.layoutYProperty().bind(chartViewer.heightProperty().subtract(70));
                    System.out.println(arrow.getImage().getWidth());
                    System.out.println(arrow.getImage().getHeight());
                    System.out.println(chartViewer.getWidth());
                    System.out.println(chartViewer.getHeight());
                    firstCall = false;
                }
                arrow.setVisible(true);
            } else {
                arrow.setVisible(false);
            }
        });

        updateService.start();


    }

    private XYPlot createStockChart() {
        XYSeries series = new XYSeries("stock");
        series.setMaximumItemCount(3600);
        XYSeries series1 = new XYSeries("red");
        series1.add(10, 10);

        XYSeries series2 = new XYSeries("green");
        series2.add(20, 20);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        ValueAxis domainAxis = new NumberAxis();
        domainAxis.setLowerBound(0);
        domainAxis.setUpperBound(60);
        domainAxis.setAutoRange(false);
        domainAxis.setTickMarksVisible(false);
//        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ((NumberAxis) domainAxis).setTickUnit(new NumberTickUnit(1));
        ((NumberAxis) domainAxis).setNumberFormatOverride(NumberFormat.getIntegerInstance());

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        XYPlot stockPlot = new XYPlot(dataset, domainAxis, null, renderer);


        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesLinesVisible(0, true);

        Shape shape = ShapeUtils.createDiagonalCross(4, 0.5f);

        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesFilled(1, true);
        renderer.setSeriesShape(1, shape);

        renderer.setSeriesPaint(2, new Color(0, 100, 0));
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesFilled(2, true);
        renderer.setSeriesShape(2, shape);

        stockPlot.setBackgroundPaint(Color.WHITE);
        stockPlot.setRangeGridlinePaint(Color.BLACK);
        stockPlot.setDomainGridlinePaint(Color.BLACK);
        stockPlot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));


        return stockPlot;
    }

    private XYPlot createMarketDepthChart() {
//      https://stackoverflow.com/questions/26941740/market-depth-chart-using-jfreechart
        XYSeries series1 = new XYSeries("red");
        series1.add(55D, 4D);
        series1.add(46D, 3D);
        series1.add(37D, 2D);
        series1.add(28D, 1D);
//        series1.setMaximumItemCount();
        XYSeries series2 = new XYSeries("green");
        series2.add(25D, 1D);
        series2.add(20D, 2D);
        series2.add(22D, 3D);
        series2.add(15D, 4D);
        series2.add(10D, 5D);
//        series2.setMaximumItemCount();
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        ValueAxis domainAxis = new NumberAxis();
        domainAxis.setAutoRange(false);
        domainAxis.setLowerBound(0);
        domainAxis.setUpperBound(60);
        domainAxis.setVisible(false);

        XYStepAreaRenderer renderer = new XYStepAreaRenderer(XYStepAreaRenderer.AREA);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(0, new Color(0, 255, 0));
//        renderer.setDefaultEntityRadius(100);
        XYPlot marketDepthChart = new XYPlot(dataset, domainAxis, null, renderer);
//        marketDepthChart.setOrientation(PlotOrientation.HORIZONTAL);
        marketDepthChart.setBackgroundPaint(Color.WHITE);
        marketDepthChart.setDomainGridlinesVisible(false);
        marketDepthChart.setRangeGridlinesVisible(false);
        marketDepthChart.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        return marketDepthChart;
    }


    private class UpdateChartsService extends ScheduledService<Data> {

        private double prevX;
        private double minVal = 44;
        private double maxVal = 45;

        @Override
        protected Task<Data> createTask() {
            return new Task<Data>() {
                @Override
                protected Data call() throws Exception {
                    double y = minVal + Math.random() * (maxVal - minVal + 1);
                    NumberAxis stockDomainAxis = (NumberAxis) ((XYPlot) plot.getSubplots().get(0)).getDomainAxis();
                    boolean isPanning = chartCanvas.isPanning();
                    boolean autoIncreasedBounds = (prevX + 1 >= (stockDomainAxis.getUpperBound() - 6 * stockDomainAxis.getTickUnit().getSize())) && !isPanning;
                    Data data = new Data(prevX, y, autoIncreasedBounds, isPanning);
                    prevX++;
                    return data;
                }
            };
        }
    }


}
