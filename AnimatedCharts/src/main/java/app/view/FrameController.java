package app.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.HiddenSidesPane;

public class FrameController {

    @FXML
    HiddenSidesPane hiddenSidesPane;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    private CheckComboBox<String> indicators;

    public void initialize() {
        configureIndicatorsComboBox();
        configureTimeComboBox();

    }

    private void configureIndicatorsComboBox(){
//        TODO: получить список индикаторов
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

    private void configureTimeComboBox() {
        timeComboBox.getItems().addAll("1min", "5min", "1h");
        timeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//          TODO: добавить действия при изменении времени
        });
    }

}
