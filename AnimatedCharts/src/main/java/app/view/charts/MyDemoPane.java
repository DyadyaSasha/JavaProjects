package app.view.charts;

import app.view.jfreechart.TaChartViewer;
import javafx.scene.layout.StackPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Crosshair;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


    public class MyDemoPane extends StackPane {

        private TaChartViewer chartViewer;

        private Crosshair xCrosshair;

        private Crosshair yCrosshair;

        private XYDataset dataset;

        public XYDataset getDataset() {
            return dataset;
        }

        public MyDemoPane() {
            dataset = createDataset();
            JFreeChart chart = createChart(dataset);
            this.chartViewer = new TaChartViewer(chart);
//            this.chartViewer.addChartMouseListener(this);
            getChildren().add(this.chartViewer);

//            TaOverlayFX crosshairOverlay = new TaOverlayFX();
//            this.xCrosshair = new Crosshair(Double.NaN, Color.BLACK,
//                    new BasicStroke(0f));
//            this.xCrosshair.setStroke(new BasicStroke(.5f));
////            this.xCrosshair.setStroke(new BasicStroke(1.5f,
////                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1,
////                    new float[]{2.0f, 2.0f}, 0));
//            this.xCrosshair.setLabelVisible(true);
//            this.yCrosshair = new Crosshair(Double.NaN, Color.BLACK,
//                    new BasicStroke(0f));
//            this.yCrosshair.setStroke(new BasicStroke(.5f));
////            this.yCrosshair.setStroke(new BasicStroke(1.5f,
////                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1,
////                    new float[] {2.0f, 2.0f}, 0));
//            this.yCrosshair.setLabelVisible(true);
//            crosshairOverlay.addDomainCrosshair(xCrosshair);
//            crosshairOverlay.addRangeCrosshair(yCrosshair);
//            this.chartViewer.getCanvas().addOverlay(crosshairOverlay);


//            Platform.runLater(() -> {
//                this.chartViewer.getCanvas().addOverlay(crosshairOverlay);
//            });
        }

//        @Override
//        public void chartMouseClicked(ChartMouseEventFX event) {
//            // ignore
//        }
//
//        @Override
//        public void chartMouseMoved(ChartMouseEventFX event) {
//            Rectangle2D dataArea = this.chartViewer.getCanvas().getRenderingInfo().getPlotInfo().getDataArea();
//            JFreeChart chart = event.getChart();
//            XYPlot plot = (XYPlot) chart.getPlot();
//            ValueAxis xAxis = plot.getDomainAxis();
//            double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea,
//                    RectangleEdge.BOTTOM);
//            // make the crosshairs disappear if the mouse is out of range
//            if (!xAxis.getRange().contains(x)) {
//                x = Double.NaN;
//            }
////            double y = DatasetUtils.findYValue(plot.getDataset(), 0, x);
//            double y = ((XYPlot) chart.getPlot()).getRangeAxis().java2DToValue(event.getTrigger().getY(), dataArea, RectangleEdge.LEFT);
//            this.xCrosshair.setValue(x);
//            this.yCrosshair.setValue(y);
//        }


    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("S1");
//        for (int x = 0; x < 100_000; x++) {
//            series.add(x, x + Math.random() * 4.0);
//        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "CrosshairOverlayDemo1", "X", "Y", dataset);
        return chart;
    }


}
