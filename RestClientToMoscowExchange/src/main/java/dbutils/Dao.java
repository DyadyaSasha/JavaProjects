package dbutils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.*;

/**
 * класс для работы с базой данный
 */
public class Dao {

    /**
     * меняем локаль, чтобы Oracle BD не ругалась на язык
     */
    static {
        Locale.setDefault(Locale.ENGLISH);
    }

    /**
     * данные, необходимые для подключения к БД
     */
    private static final String DB_USER = "MOSCOWEXCHANGE";
    private static final String DB_PASS = "MOSCOWEXCHANGE";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

    /**
     * объект, в котором находится пулл готовый коннектов к БД
     */
    private BasicDataSource basicDataSource = new BasicDataSource();

    /**
     * определяем объект basicDataSource
     */
    private Dao() {
        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(DB_USER);
        basicDataSource.setPassword(DB_PASS);
        /**
         * устанавливаем кол-во коннектов в базе, которое будет хранится в пуле
         */
        basicDataSource.setInitialSize(5);
    }

    private static class DaoHolder {
        private static final Dao INSTANCE = new Dao();
    }

    public static Dao getInstance() {
        return DaoHolder.INSTANCE;
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }


    /**
     * Метод очищающий таблицы из БД, которые(таблицы) есть в JSON объекте
     * @param connectionFromPool - коннект к БД из пула коннектов
     * @param tableNames - имена таблиц, получаемые из JSON объекта
     * @throws SQLException
     */
    public static void clearDB(Connection connectionFromPool, Set<String> tableNames) throws SQLException {

        try (Connection connection = connectionFromPool) {
            for (Iterator<String> iterator = tableNames.iterator(); iterator.hasNext(); ) {
                String tableName = iterator.next();
                try (Statement statement = connection.createStatement()) {
                    String deleteQuery = "DROP TABLE " + tableName;
                    statement.executeUpdate(deleteQuery);
                } catch (SQLSyntaxErrorException e) {}
            }
        }

    }

    /**
     * создание таблицы
     * @param connectionFromPool - коннект к БД из пула коннектов
     * @param tableName - имя таблицы, получаемое из JSON объекта
     * @param columns - массив столбцов в таблице
     * @param metadata - метаданные таблицы(тип данных столбцов)
     * @throws SQLException
     */
    public static void createTable(Connection connectionFromPool, String tableName, List<String> columns, Map<String, Map<String, String>> metadata) throws SQLException {

        try (Connection connection = connectionFromPool) {
            try (Statement statement = connection.createStatement()) {
                StringBuilder createQuery = new StringBuilder("CREATE TABLE " + tableName + " (");
                for (String column : columns) {
                    createQuery.append(" " + column);
                    String type = metadata.get(column).get("type");
                    if (type.contains("int")) {
                        createQuery.append(" INTEGER,");
                    } else if (type.contains("string")) {
                        createQuery.append(" VARCHAR(255),");
                    }
                }
                createQuery.replace(createQuery.length() - 1, createQuery.length(), " )");
                statement.executeUpdate(createQuery.toString());
            }
        }

    }

    /**
     * вставлеям строки в таблицу
     * @param connectionFromPool - коннект к БД из пула коннектов
     * @param tableName - имя таблицы, получаемое из JSON объекта
     * @param columns - массив столбцов в таблице
     * @param metadata - метаданные таблицы(тип данных столбцов)
     * @param data - массив данных(строк) для сохранения в таблицу
     * @throws SQLException
     */
    public static void insertData(Connection connectionFromPool, String tableName, List<String> columns, Map<String, Map<String, String>> metadata, List<List<Object>> data) throws SQLException {

        try (Connection connection = connectionFromPool) {

            StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + "( " + String.join(", ", columns) + " ) VALUES ( ");

            String wildCardArray[] = new String[columns.size()];
            for (int i = 0; i < columns.size(); i++) {
                wildCardArray[i] = "?";
            }
            insertQuery.append(String.join(", ", wildCardArray) + " )");

            for (List<Object> row : data) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString())) {
                    for (int i = 0; i < row.size(); i++) {
                        String type = metadata.get(columns.get(i)).get("type");
                        if (type.trim().contains("string")) {
                            if (row.get(i) != null) {
                                preparedStatement.setString(i + 1, (String) row.get(i));
                            } else {
                                preparedStatement.setNull(i + 1, Types.VARCHAR);
                            }
                        } else if (type.trim().contains("int")) {
                            if (row.get(i) != null) {
                                preparedStatement.setInt(i + 1, ((Double) row.get(i)).intValue());
                            } else {
                                preparedStatement.setNull(i + 1, Types.INTEGER);
                            }
                        }
                    }
                    preparedStatement.executeUpdate();
                }
            }
        }

    }

}
