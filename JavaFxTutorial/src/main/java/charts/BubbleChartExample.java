package charts;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BubbleChartExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        NumberAxis xAxis = new NumberAxis(0, 100, 10);
        xAxis.setLabel("Age");

        NumberAxis yAxis = new NumberAxis(20,100,10);
        yAxis.setLabel("Weight");

        BubbleChart bubbleChart = new BubbleChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("work");

        series.getData().add(new XYChart.Data(10,30,4));
        series.getData().add(new XYChart.Data(25,40,5));
        series.getData().add(new XYChart.Data(40,50,9));
        series.getData().add(new XYChart.Data(55,60,7));
        series.getData().add(new XYChart.Data(70,70,9));
        series.getData().add(new XYChart.Data(85,80,6));

        bubbleChart.getData().add(series);

        Group root = new Group(bubbleChart);
        Scene scene = new Scene(root, 600,400);

        primaryStage.setTitle("Bubble Chart");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
