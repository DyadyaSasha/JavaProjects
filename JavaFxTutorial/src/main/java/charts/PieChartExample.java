package charts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * данные для графика
         */
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Iphone 5S", 13),
                new PieChart.Data("Samsung Grand", 25),
                new PieChart.Data("MOTO G", 10),
                new PieChart.Data("Nokia Lumia", 22)
        );

        PieChart pieChart = new PieChart(pieChartData);
//        pieChart.setData(pieChartData);

        pieChart.setTitle("Mobile phones");
        /**
         * направление отображения данных по часовой стрелке
         */
        pieChart.setClockwise(true);

        /**
         * длина линии подписи
         */
        pieChart.setLabelLineLength(50);

        /**
         * делаем надписи видимыми
         */
        pieChart.setLabelsVisible(true);

        /**
         * угол, с которого будут отображаться данные
         */
        pieChart.setStartAngle(180);

        Group root = new Group(pieChart);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Pie chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
