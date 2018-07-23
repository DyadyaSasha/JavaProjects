package jfreechart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

public class LineChartExample extends Application {

//    public JFreeChart createChart(){
//        XYDataset dataset = new
//        JFreeChart chart = ChartFactory.createXYLineChart(null, null, null);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new Scene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
