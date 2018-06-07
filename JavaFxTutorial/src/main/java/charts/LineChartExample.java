package charts;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * задаём интервал и шаг оси
         */
        NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
        /**
         * подпись оси
         */
        xAxis.setLabel("Years");

        NumberAxis yAxis = new NumberAxis(0,350,50);
        yAxis.setLabel("No.of schools");

        /**
         * объект линейного графика
         */
        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        /**
         * имя графика
         */
        series.setName("No of schools in an year");
        /**
         * задаём значения для графика
         */
        series.getData().add(new XYChart.Data(1970, 15));
        series.getData().add(new XYChart.Data(1980, 30));
        series.getData().add(new XYChart.Data(1990, 60));
        series.getData().add(new XYChart.Data(2000, 120));
        series.getData().add(new XYChart.Data(2013, 240));
        series.getData().add(new XYChart.Data(2014, 300));

        lineChart.getData().add(series);

        Group root = new Group(lineChart);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Line Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
