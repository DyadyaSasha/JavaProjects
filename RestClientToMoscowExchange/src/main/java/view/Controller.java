package view;

import com.google.gson.Gson;
import dbutils.Dao;
import dbutils.Data;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.apache.commons.dbcp2.BasicDataSource;
import restclient.Requests;

import java.sql.SQLException;
import java.util.*;

public class Controller {

    private Dao dao = Dao.getInstance();
    private List<Data> tablesData;

    @FXML
    private TableView<List<String>> table;

    @FXML
    ChoiceBox<String> tableList;

    @FXML
    private Label status;

    @FXML
    private void setStatus(MouseEvent mouseEvent) {
        status.setText("Загрузка...");
    }


    public void initialize() {

        tableList.getSelectionModel().selectedItemProperty().addListener(new SelectChoice());

        List<String> tableNames = null;

        try {
            tableNames = dao.getNamesOfTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tableNames != null && !tableNames.isEmpty()) {
            tableList.setItems(FXCollections.observableArrayList(tableNames));
            tableList.setDisable(false);
        } else {
            tableList.setDisable(true);
        }

        try {
            tablesData = dao.getTablesData();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * при нажатии на кнопку "Загрузить данные" вызывается данный метод:
     * <p>
     * 1)сначала происходит запрос к Rest серверу с помощью метода getExchangeInformation()
     * из класса {@link Requests};
     * <p>
     * 2) затем JSON строка с помощью библиотеки Gson, которая используется для работы c JSON-ом,
     * преобразуется в {@link Map} для удобного итерирования по элементам
     * <p>
     * 3) очищаем из БД те таблицы, которые мы получили с Rest сервера с помощью метода clearDB()
     * из класса {@link Dao}
     * <p>
     * 4) в цикле итерируемся по {@link Map}, ключи которой - названия таблиц,
     * а значения - объекты метаданных(здесь указывается, какой тип имеет конкретный столбец в таблице ), значения строк и объекты, содержащие порядок столбцов в таблице
     * <p>
     * 4.1) создаём новую таблицы с помощью метода createTable()
     * из класса {@link Dao}
     * <p>
     * 4.2) вставляем новые значения в таблицу с помощью метода insertData()
     * из класса {@link Dao}
     *
     * @param mouseEvent
     * @throws SQLException
     */
    @FXML
    private void download(MouseEvent mouseEvent) throws SQLException {

        BasicDataSource basicDataSource = dao.getBasicDataSource();

        Map<String, Object> jsonData = new Gson().fromJson(new Requests().getExchangeInformation(), new HashMap<String, String>().getClass());

        dao.clearDB(basicDataSource.getConnection(), jsonData.keySet());


        for (String tableName : jsonData.keySet()) {

            Map<String, Object> entityValues = (Map<String, Object>) jsonData.get(tableName);

            Map<String, Map<String, String>> metadata = null;
            List<String> columns = null;
            List<List<Object>> data = null;

            for (String key : entityValues.keySet()) {
                if (key.trim().equals("metadata")) {
                    metadata = (Map<String, Map<String, String>>) entityValues.get(key);
                } else if (key.trim().equals("columns")) {
                    columns = (ArrayList<String>) entityValues.get(key);
                } else if (key.trim().equals("data")) {
                    data = (List<List<Object>>) entityValues.get(key);
                }
            }


            dao.createTable(basicDataSource.getConnection(), tableName, columns, metadata);

            dao.insertData(basicDataSource.getConnection(), tableName, columns, metadata, data);
        }

        //обновляем список таблиц в ChoiceBox
        List<String> tableNames = null;

        try {
            tableNames = dao.getNamesOfTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tableNames != null && !tableNames.isEmpty()) {
            tableList.getItems().clear();
            tableList.setItems(FXCollections.observableArrayList(tableNames));
            tableList.setDisable(false);
        } else {
            tableList.setDisable(true);
        }

        //обновляем данные для TableView(список столбцов и сами данные)
        try {
            tablesData = dao.getTablesData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        status.setText("Загрузка завершена");

    }

    class SelectChoice implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            table.getColumns().clear();
            table.getItems().clear();

            if (newValue == null) {
                return;
            }

            for (Data tableData : tablesData) {
                if (tableData.getTableName().trim().equalsIgnoreCase(newValue.trim())) {

                    for (int index = 0; index < tableData.getColumnNames().size(); index++) {
                        TableColumn<List<String>, String> column = new TableColumn<>(tableData.getColumnNames().get(index));
                        final int i = index;
                        column.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(i)));
                        table.getColumns().add(column);
                    }

                    for (LinkedList<String> row : tableData.getDataRows()) {
                        table.getItems().add(row);
                    }
                }
            }
        }
    }
}
