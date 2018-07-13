package examples;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DraggingLineChartSample extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis(0, 12, 1);
        final NumberAxis yAxis = new NumberAxis(0, 50, 5);
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        lineChart.setAnimated(false);

        //defining a series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data<>(1, 23));
        series.getData().add(new XYChart.Data<>(2, 14));
        series.getData().add(new XYChart.Data<>(3, 15));
        series.getData().add(new XYChart.Data<>(4, 24));
        series.getData().add(new XYChart.Data<>(5, 34));
        series.getData().add(new XYChart.Data<>(6, 36));
        series.getData().add(new XYChart.Data<>(7, 22));
        series.getData().add(new XYChart.Data<>(8, 45));
        series.getData().add(new XYChart.Data<>(9, 43));
        series.getData().add(new XYChart.Data<>(10, 17));
        series.getData().add(new XYChart.Data<>(11, 29));
        series.getData().add(new XYChart.Data<>(12, 25));

        Scene scene = new Scene(new BorderPane(lineChart), 800, 600);
        lineChart.getData().add(series);

        for (Data<Number, Number> data : series.getData()) {
            Node node = data.getNode();
            node.setCursor(Cursor.HAND);
            node.setOnMouseDragged(e -> {
                Point2D pointInScene = new Point2D(e.getSceneX(), e.getSceneY());
                double xAxisLoc = xAxis.sceneToLocal(pointInScene).getX();
                double yAxisLoc = yAxis.sceneToLocal(pointInScene).getY();
                Number x = xAxis.getValueForDisplay(xAxisLoc);
                Number y = yAxis.getValueForDisplay(yAxisLoc);
                data.setXValue(x);
                data.setYValue(y);
            });
        }


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
