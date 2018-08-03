package app.view;

import app.view.charts.JfreeStockLineChart;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class JfreeController {

    private int chartsCount = 1;

    @FXML
    private VBox chartsArea;

    @FXML
    private ScrollPane chartScroll;

    public void initialize(){

        chartsArea.heightProperty().addListener((observable, oldValue, newValue) -> {
            double chartMaxHeight = (double) newValue / (double) chartsCount;
            chartsArea.getChildren().forEach(node -> {
                ((JfreeStockLineChart)node).setMaxHeight(chartMaxHeight);
                ((JfreeStockLineChart)node).setMinHeight(chartMaxHeight);
            });
        });

        JfreeStockLineChart stockLineChart = new JfreeStockLineChart();
        chartsArea.getChildren().add(stockLineChart);
    }

    @FXML
    public void addNewChart(){
        double chartsAreaHeight = chartsArea.getHeight();
        chartsCount += 1;
        double chartMaxHeight;
        if(chartsCount > 2){
            chartMaxHeight = ((JfreeStockLineChart)chartsArea.getChildren().get(0)).getMaxHeight();
        } else {
            chartMaxHeight = chartsAreaHeight / (double) chartsCount;
        }

        JfreeStockLineChart chart = new JfreeStockLineChart();
        chartsArea.getChildren().add(chart);
        chartsArea.getChildren().forEach(node -> {
            ((JfreeStockLineChart)node).setMaxHeight(chartMaxHeight);
            ((JfreeStockLineChart)node).setMinHeight(chartMaxHeight);
        });
    }


}
