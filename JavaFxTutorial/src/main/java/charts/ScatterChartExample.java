package charts;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ScatterChartExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        NumberAxis xAxis = new NumberAxis(0,12,3);
        xAxis.setLabel("Area");

        NumberAxis yAxis = new NumberAxis(0,16,4);
        yAxis.setLabel("Weight");

        ScatterChart<String, Number> scatterChart = new ScatterChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(8, 12));
        series.getData().add(new XYChart.Data(4, 5.5));
        series.getData().add(new XYChart.Data(11, 14));
        series.getData().add(new XYChart.Data(4, 5));
        series.getData().add(new XYChart.Data(3, 3.5));
        series.getData().add(new XYChart.Data(6.5, 7));

        scatterChart.getData().addAll(series);

        Group root = new Group(scatterChart);
        Scene scene = new Scene(root, 600,400);
        primaryStage.setTitle("Scatter Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
