package utils;

import data.DataSource;

import java.sql.*;
import java.util.List;

import static jdbc.DBConstants.TABLE_NAME;

public class JDBCHelperImpl implements JDBCHelper{

    private Connection connection;

    public JDBCHelperImpl(){}

    public JDBCHelperImpl(Connection connection){
        this.connection = connection;
    }

    public void deleteRows() throws SQLException{

        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM sites";
        ResultSet result = statement.executeQuery(sqlQuery);
        if(result.next()){
//            Можно использовать TRUNCATE, у которой нет rolback-а
            String sqlDeleteRows = "DELETE FROM sites";
            statement.executeUpdate(sqlDeleteRows); // возвращает число удалённых/изменившихся строк
            result.close();
            statement.close();
        }

    }

    public void createTable() throws SQLException{

        Statement statement = connection.createStatement();
//      нужен autoincrement ключ
        String sqlCreate = "CREATE TABLE  sites " +
                "(" +
                "title VARCHAR(255) not NULL," +
                " href VARCHAR(255) not NULL" +
                ")";
        statement.execute(sqlCreate); // возвращаеет булево значение - удалось/не удалось создать таблицу
        statement.close();

    }

    public void createTableV2() throws SQLException{

        Statement statement = connection.createStatement();
//      нужен autoincrement ключ
        String sqlCreate = "CREATE TABLE IF NOT EXISTS sites " +
                "(" +
                " title VARCHAR(255) not NULL," +
                " href VARCHAR(255) not NULL" +
                " )";
        statement.execute(sqlCreate);
        statement.close();

    }


    public boolean isTableExist(String tabelLabel) throws SQLException {

        Statement statement = connection.createStatement();
        String query = "SHOW TABLES";
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            if (result.getString(tabelLabel).equalsIgnoreCase(TABLE_NAME)){
                result.close();
                statement.close();
                return true;
            }
        }

        result.close();
        statement.close();
        return false;

    }

    public void inMemoryInsert(List<DataSource> ds) throws SQLException{

        String sqlMerge = "MERGE INTO sites KEY (title) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlMerge);
        for (DataSource site : ds){
            preparedStatement.setString(1, site.getTitle());
            preparedStatement.setString(2, site.getHref());
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();

    }

    public void inPersistentInsert(Connection h2Conn) throws SQLException{

        String sqlMerge = "MERGE INTO sites KEY (title) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlMerge);
        String h2Query = "SELECT * FROM sites";
        Statement h2Stat = h2Conn.createStatement();
        ResultSet h2Result = h2Stat.executeQuery(h2Query);
        while(h2Result.next()){
            preparedStatement.setString(1,
                    h2Result.getString("title"));
            preparedStatement.setString(2,
                    h2Result.getString("href"));
            preparedStatement.executeUpdate();
        }
        h2Result.close();
        preparedStatement.close();
        h2Stat.close();

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
