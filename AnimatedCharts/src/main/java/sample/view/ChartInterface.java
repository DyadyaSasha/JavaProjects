package sample.view;

import javafx.scene.chart.XYChart;

public interface ChartInterface {

    XYChart<?,?> getChart();
    double getXTick();
    void setXTick(double xTick);
    double getPrevX();
    XYChart.Series<Number, Number> getDataSeries();
}