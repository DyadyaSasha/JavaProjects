package dbutils;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * класс для хранения имени таблицы, списка её столбцов и списка строк с данными
 */
public class Data {

    private String tableName;
    private List<String> columnNames;
    private List<LinkedList<String>> dataRows;

    public Data(String tableName) {
        this.tableName = tableName;
        columnNames = new LinkedList<>();
        dataRows = new ArrayList<>();
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<LinkedList<String>> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<LinkedList<String>> dataRows) {
        this.dataRows = dataRows;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
