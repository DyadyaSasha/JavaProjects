package app.view;

import app.models.BalancesData;
import app.models.MarketsData;
import app.models.OrdersTreeData;
import app.view.charts.JfreeStockLineChart;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.SegmentedButton;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.util.Arrays;

public class JfreeController {

    private int chartsCount = 1;
    private byte windowsCount = 1;

    @FXML
    private TreeTableView<OrdersTreeData> ordersTable;

    @FXML
    private TextArea logArea;

    @FXML
    private TableView<MarketsData> marketsTable;

    @FXML
    private TableView<BalancesData> balancesTable;

    @FXML
    private ComboBox<String> secondTimeColumn, thirdTimeColumn,
            coinName, timeComboBox, balancesCoinName, amountBox;

    @FXML
    private TextField marketsCoinSearch, balancesCoinSearch;

    @FXML
    private Button newWindow;

    @FXML
    private SegmentedButton typeChartButtons;


    @FXML
    private CheckComboBox<String> indicators;

    @FXML
    private VBox chartsArea;

    @FXML
    private ScrollPane chartScroll;

    public void initialize() {

        configureMarketsTab();
        configureTypeChartButtonGroup();
        configureTimeComboBox();
        configureIndicatorsComboBox();
        configureBalancesTab();
        configureOrdersBottomTab();
        configureOrdersRightTab();
        configureTradeTab();
        configureLogTab();

        chartsArea.heightProperty().addListener((observable, oldValue, newValue) -> {
            double chartMaxHeight = (double) newValue / (double) chartsCount;
            chartsArea.getChildren().forEach(node -> {
                ((JfreeStockLineChart) node).setMaxHeight(chartMaxHeight);
                ((JfreeStockLineChart) node).setMinHeight(chartMaxHeight);
            });
        });

        JfreeStockLineChart stockLineChart = new JfreeStockLineChart();
        chartsArea.getChildren().add(stockLineChart);
    }

    @FXML
    public void addNewChart() {
        double chartsAreaHeight = chartsArea.getHeight();
        chartsCount += 1;
        double chartMaxHeight;
        if (chartsCount > 2) {
            chartMaxHeight = ((JfreeStockLineChart) chartsArea.getChildren().get(0)).getMaxHeight();
        } else {
            chartMaxHeight = chartsAreaHeight / (double) chartsCount;
        }

        JfreeStockLineChart chart = new JfreeStockLineChart();
        chartsArea.getChildren().add(chart);
        chartsArea.getChildren().forEach(node -> {
            ((JfreeStockLineChart) node).setMaxHeight(chartMaxHeight);
            ((JfreeStockLineChart) node).setMinHeight(chartMaxHeight);
        });
    }

    /**
     * ИНДИКАТОРЫ
     */

    private void configureIndicatorsComboBox() {

//      TODO: получить список индикаторов
        indicators.getItems().addAll("EMA", "RSI", "MACD",
                "AO", "Stoch");

        indicators.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> {
            while (change.next()) {
                String indicatorName;
                if (change.wasAdded()) {
                    indicatorName = change.getAddedSubList().get(0);
                    addIndicatorPlot(indicatorName);

                }
                if (change.wasRemoved()) {
                    indicatorName = change.getRemoved().get(0);
                    removeIndicatorPlot(indicatorName);
                }
            }
        });
    }

    private void addIndicatorPlot(String indicatorName) {

    }

    private void removeIndicatorPlot(String indicatorName) {

    }

    /**
     * ВРЕМЯ(ВСПЛЫВАЮЩИЙ СПИСОК)
     */
    private void configureTimeComboBox() {
        timeComboBox.getItems().addAll("1min", "5min", "1h");
        timeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: добавить действия при изменении времени
        });
    }

    /**
     * ПЕРЕКЛЮЧАТЕЛЬ МЕЖДУ ТИПАМИ ГРАФИКОВ
     */
    private void configureTypeChartButtonGroup() {
        typeChartButtons.getButtons().forEach(toggleButton -> toggleButton.isSelected());
    }

    @FXML
    private void lineaction(ActionEvent event) {
    }


    /**
     * ОТКРЫТИЕ ГРАФИКА В НОВОМ ОКНЕ
     */
    @FXML
    private void openChartInNewFrame(ActionEvent event) {

        windowsCount++;
        if (windowsCount == AppConstants.MAX_WINDOWS_COUNT) {
            newWindow.setDisable(true);
        }

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("templates/frame.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Coin name");
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(windowEvent -> {
                windowsCount--;
                if (windowsCount == AppConstants.MAX_WINDOWS_COUNT - 1) {
                    newWindow.setDisable(false);
                }

            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * КНОПКИ ЗУМИРОВАНИЯ
     */


    /**
     * ВКЛАДКА MARKETS
     */

    private void configureMarketsTab() {
//      TODO: получить список валют
        coinName.getItems().addAll("BTC", "ETH", "BNB");
        coinName.getSelectionModel().select("BTC");
        coinName.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: действия при изменении монеты
        });

        secondTimeColumn.getItems().addAll("1min", "5min");
//        secondTimeColumn.getSelectionModel().select("");
        secondTimeColumn.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: действия при изменении второго столбца времени
        });

        thirdTimeColumn.getItems().addAll("1min", "5min");

        thirdTimeColumn.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: действия при изменении третьего столбца времени
        });

//      TODO: получить список альтов
        TextFields.bindAutoCompletion(marketsCoinSearch, Arrays.asList("Alt", "arc", "alf", "axe"));
        marketsCoinSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//         TODO: действия при наборе в текстовом поле(сортировка таблицы)
        });

//        TODO: таблица с данными по рынку (marketsTable)

    }

    private void configureBalancesTab() {

//      TODO: получить список альтов
        TextFields.bindAutoCompletion(balancesCoinSearch, Arrays.asList("Alt", "arc", "alf", "axe"));
        balancesCoinSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//        TODO: действия при наборе в текстовом поле(сортировка таблицы)
        });

        amountBox.getItems().addAll("Total", "Free", "In Order");
        amountBox.getSelectionModel().select(0);
        amountBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: действия при изменении
        });


        balancesCoinName.getItems().addAll("BTC", "ETH", "BNB");
        balancesCoinName.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: действия при изменении
        });


//      TODO: действия над таблицой (balancesTable)
    }

    private void configureOrdersBottomTab(){

    }

    private void configureOrdersRightTab(){

    }

    private void configureTradeTab(){

    }

    private void configureLogTab(){

    }
}
