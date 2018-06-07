package view;

import com.google.gson.Gson;
import dbutils.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.apache.commons.dbcp2.BasicDataSource;
import restclient.Requests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    @FXML
    private Label status;

    @FXML
    private void setStatus(MouseEvent mouseEvent) {
        status.setText("Загрузка...");
    }

    /**
     * при нажатии на кнопку "Загрузить данные" вызывается данный метод:
     *
     * 1)сначала происходит запрос к Rest серверу с помощью метода getExchangeInformation()
     *      из класса {@link Requests};
     *
     * 2) затем JSON строка с помощью библиотеки Gson, которая используется для работы c JSON-ом,
     *      преобразуется в {@link Map} для удобного итерирования по элементам
     *
     * 3) очищаем из БД те таблицы, которые мы получили с Rest сервера с помощью метода clearDB()
     *      из класса {@link Dao}
     *
     * 4) в цикле итерируемся по {@link Map}, ключи которой - названия таблиц,
     *      а значения - объекты метаданных(здесь указывается, какой тип имеет конкретный столбец в таблице ), значения строк и объекты, содержащие порядок столбцов в таблице
     *
     *      4.1) создаём новую таблицы с помощью метода createTable()
     *          из класса {@link Dao}
     *
     *      4.2) вставляем новые значения в таблицу с помощью метода insertData()
     *          из класса {@link Dao}
     * @param mouseEvent
     * @throws SQLException
     */
    @FXML
    private void download(MouseEvent mouseEvent) throws SQLException {

        Dao dao = Dao.getInstance();
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

        status.setText("Загрузка завершена");
    }

}
