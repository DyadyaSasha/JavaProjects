package examples;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class PointEverySecondLineChart extends Application {
    private BorderPane root = new BorderPane();
    private Scene scene = new Scene(root,800,600);

    private Random rand = new Random();

    private SimpleIntegerProperty
            lastX = new SimpleIntegerProperty(0);
    private XYChart.Series <Integer,Integer> mySeries;
    private NumberAxis
            xAxis = new NumberAxis(),
            yAxis = new NumberAxis();
    private LineChart lineChart = new LineChart<>(xAxis,yAxis);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Timeline addPointEverySecond = new Timeline(new KeyFrame(Duration.millis(500), event->{
            lastX.set(lastX.add(1).get());
            mySeries.getData().add(new XYChart.Data<>(lastX.get(), rand.nextInt(100)));
            if (mySeries.getData().size()>10) mySeries.getData().remove(0);
        }));
        addPointEverySecond.setCycleCount(Timeline.INDEFINITE);
        ObservableList<XYChart.Series<Integer,Integer>> data = FXCollections.observableArrayList();

        LineChart<Integer,Integer> chart = makeLineChart(data);
        chart.setPrefWidth(600);
        chart.setPrefHeight(600);
        root.setCenter(chart);

        Button btGo = new Button("GO!");
        btGo.setOnAction(action -> {
            mySeries = new XYChart.Series<>();
            data.add(mySeries);
            lastX.set(0);
            addPointEverySecond.playFromStart();
        });

        btGo.disableProperty().bind(addPointEverySecond.statusProperty().isEqualTo(Animation.Status.RUNNING));
        root.setBottom(btGo);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    LineChart<Integer, Integer> makeLineChart(ObservableList <XYChart.Series<Integer,Integer>> series) {
        xAxis.setForceZeroInRange(false);
        lineChart.setCreateSymbols(false);
        lineChart.setData(series);
        return lineChart;
    }

}