package jfreechart;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SwingNodeExample extends Application {


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


    @Override
    public void start(Stage primaryStage) throws Exception {
        SwingNode swingNode = new SwingNode();
        MyDemoPanel myDemoPanel = new MyDemoPanel();
        SwingUtilities.invokeLater(() -> swingNode.setContent(myDemoPanel));
        Pane pane = new Pane();

//        pane.prefHeightProperty().bind(primaryStage.heightProperty());
//        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.getChildren().add(swingNode);
//        pane.setStyle("-fx-border-color: red");
//        swingNode.setStyle("-fx-border-color: blue");
//        pane.widthProperty().addListener((observable, oldValue, newValue) -> myDemoPanel.chartPanel.setSize(newValue.intValue(), (int)pane.getHeight()));
//        pane.heightProperty().addListener((observable, oldValue, newValue) -> myDemoPanel.chartPanel.setSize((int)pane.getWidth(), newValue.intValue()));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
