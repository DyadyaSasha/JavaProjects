package app.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import app.view.charts.StockLineChart;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public List<HBox> stockLineCharts;

    @FXML
    private VBox chartsFlow;

    @FXML
    private Button newWindow;

    public void initialize() {

        newWindow.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/static/newWindow.png"))));
        stockLineCharts = new ArrayList<>();
        addNewChart();
    }

    @FXML
    private void addNewChart() {
        StockLineChart stockLineChart = new StockLineChart();
//        StockLineChartGrid stockLineChart = new StockLineChartGrid();
        stockLineCharts.add(stockLineChart);
        chartsFlow.getChildren().add(stockLineChart);
        stockLineChart.play();
    }

    @FXML
    public void setMaxBounds(ActionEvent actionEvent) {

//        ((ToggleButton)actionEvent.getSource()).setSelected(true);


        stockLineCharts.forEach(chart -> {

            StockLineChart stockLineChart = (StockLineChart) chart;
//            StockLineChartGrid stockLineChart = (StockLineChartGrid) chart;
            LineChart<Number, Number> lineChart = stockLineChart.getChart();

            ValueAxis<?> xAxis = ((ValueAxis<?>) lineChart.getXAxis());
            final double newXLowerBound = xAxis.getUpperBound() - stockLineChart.getInitialTimeUpperBound();
            if(newXLowerBound < 0){
                xAxis.setLowerBound(stockLineChart.getInitialTimeLowerBound());
            } else {
                xAxis.setLowerBound(newXLowerBound);
            }


            ValueAxis<?> yAxis = ((ValueAxis<?>) lineChart.getYAxis());
            yAxis.setLowerBound(stockLineChart.getInitialPriceLowerBound());
            yAxis.setUpperBound(stockLineChart.getInitialPriceUpperBound());

        });
    }

    @FXML
    public void setHalfBounds(ActionEvent actionEvent) {
//        ((ToggleButton)actionEvent.getSource()).setSelected(true);

        stockLineCharts.forEach(chart -> {

            StockLineChart stockLineChart = (StockLineChart) chart;
//            StockLineChartGrid stockLineChart = (StockLineChartGrid) chart;
            LineChart<Number, Number> lineChart = stockLineChart.getChart();

            final double lastY = ((StockLineChart) chart).getLastY();
//            final double lastY = ((StockLineChartGrid) chart).getLastY();


            ValueAxis<?> yAxis = ((ValueAxis<?>) lineChart.getYAxis());

            yAxis.setLowerBound(lastY*0.5);
            yAxis.setUpperBound(lastY*1.5);

            ValueAxis<?> xAxis = ((ValueAxis<?>) lineChart.getXAxis());
//            ((ValueAxis<?>) ((StockLineChart) chart).getChart().getXAxis()).setLowerBound();
//            ((ValueAxis<?>) ((StockLineChart) chart).getChart().getXAxis()).setUpperBound();



        });
    }

    @FXML
    public void setTwentyBounds(ActionEvent actionEvent) {
//        ((ToggleButton)actionEvent.getSource()).setSelected(true);
        stockLineCharts.forEach(chart -> {

            StockLineChart stockLineChart = (StockLineChart) chart;
//            StockLineChartGrid stockLineChart = (StockLineChartGrid) chart;
            LineChart<Number, Number> lineChart = stockLineChart.getChart();

            final double lastY = ((StockLineChart) chart).getLastY();
//            final double lastY = ((StockLineChartGrid) chart).getLastY();


            ValueAxis<?> yAxis = ((ValueAxis<?>) lineChart.getYAxis());

            yAxis.setLowerBound(lastY*0.9);
            yAxis.setUpperBound(lastY*1.1);

            ValueAxis<?> xAxis = ((ValueAxis<?>) lineChart.getXAxis());
//            ((ValueAxis<?>) ((StockLineChart) chart).getChart().getXAxis()).setLowerBound();
//            ((ValueAxis<?>) ((StockLineChart) chart).getChart().getXAxis()).setUpperBound();



        });
    }

    @FXML
    public void setTenBounds(ActionEvent actionEvent) {
//        ((ToggleButton)actionEvent.getSource()).setSelected(true);
        stockLineCharts.forEach(chart -> {

            StockLineChart stockLineChart = (StockLineChart) chart;
//            StockLineChartGrid stockLineChart = (StockLineChartGrid) chart;
            LineChart<Number, Number> lineChart = stockLineChart.getChart();

            final double lastY = ((StockLineChart) chart).getLastY();
//            final double lastY = ((StockLineChartGrid) chart).getLastY();

            ValueAxis<?> yAxis = ((ValueAxis<?>) lineChart.getYAxis());

            yAxis.setLowerBound(lastY*0.95);
            yAxis.setUpperBound(lastY*1.05);

            ValueAxis<?> xAxis = ((ValueAxis<?>) lineChart.getXAxis());
//            ((ValueAxis<?>) ((StockLineChart) chart).getChart().getXAxis()).setLowerBound();
//            ((ValueAxis<?>) ((StockLineChart) chart).getChart().getXAxis()).setUpperBound();



        });
    }


    private void removeChart(HBox chart) {
        stockLineCharts.remove(chart);
        chartsFlow.getChildren().remove(chart);
    }


    @FXML
    private void openNewWindow(ActionEvent actionEvent) {
    }
}
