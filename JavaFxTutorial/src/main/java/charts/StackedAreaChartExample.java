package charts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;

public class StackedAreaChartExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("1750", "1800", "1850", "1900", "1950", "1999", "2050")));

        NumberAxis yAxis = new NumberAxis(0, 10000, 2500);
        yAxis.setLabel("Population in Millions");

        StackedAreaChart<String, Number> areaChart = new StackedAreaChart<String, Number>(xAxis, yAxis);
        areaChart.setTitle("Historic and Estimated Worldwide Population Growth by Region");

        XYChart.Series series1 = new XYChart.Series();

        series1.setName("Asia");
        series1.getData().add(new XYChart.Data("1750", 502));
        series1.getData().add(new XYChart.Data("1800", 635));
        series1.getData().add(new XYChart.Data("1850", 809));
        series1.getData().add(new XYChart.Data("1900", 947));
        series1.getData().add(new XYChart.Data("1950", 1402));
        series1.getData().add(new XYChart.Data("1999", 3634));
        series1.getData().add(new XYChart.Data("2050", 5268));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Africa");
        series2.getData().add(new XYChart.Data("1750", 106));
        series2.getData().add(new XYChart.Data("1800", 107));
        series2.getData().add(new XYChart.Data("1850", 111));
        series2.getData().add(new XYChart.Data("1900", 133));
        series2.getData().add(new XYChart.Data("1950", 221));
        series2.getData().add(new XYChart.Data("1999", 767));
        series2.getData().add(new XYChart.Data("2050", 1766));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Europe");

        series3.getData().add(new XYChart.Data("1750", 163));
        series3.getData().add(new XYChart.Data("1800", 203));
        series3.getData().add(new XYChart.Data("1850", 276));
        series3.getData().add(new XYChart.Data("1900", 408));
        series3.getData().add(new XYChart.Data("1950", 547));
        series3.getData().add(new XYChart.Data("1999", 729));
        series3.getData().add(new XYChart.Data("2050", 628));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("America");
        series4.getData().add(new XYChart.Data("1750", 18));
        series4.getData().add(new XYChart.Data("1800", 31));
        series4.getData().add(new XYChart.Data("1850", 54));
        series4.getData().add(new XYChart.Data("1900", 156));
        series4.getData().add(new XYChart.Data("1950", 339));
        series4.getData().add(new XYChart.Data("1999", 818));
        series4.getData().add(new XYChart.Data("2050", 1201));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Oceania");
        series5.getData().add(new XYChart.Data("1750", 2));
        series5.getData().add(new XYChart.Data("1800", 2));
        series5.getData().add(new XYChart.Data("1850", 2));
        series5.getData().add(new XYChart.Data("1900", 6));
        series5.getData().add(new XYChart.Data("1950", 13));
        series5.getData().add(new XYChart.Data("1999", 30));
        series5.getData().add(new XYChart.Data("2050", 46));

        areaChart.getData().addAll(series1,series2,series3,series4,series5);

        Group root = new Group(areaChart);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Area Chart");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
