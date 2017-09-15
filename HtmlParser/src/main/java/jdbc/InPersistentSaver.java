package jdbc;

import utils.JDBCHelperImpl;

import java.sql.*;

import static jdbc.DBConstants.*;
import static jdbc.DBConstants.H2_PASSWORD;
import static jdbc.DBConstants.H2_USER;

public class InPersistentSaver {

    public void save(){

//        В новых версиях JDBC не обязательно регистрировать(создавать объект JDBC-драйвера)
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//        } catch (ClassNotFoundException | InstantiationException
//                | IllegalAccessException e) {
//            e.printStackTrace();
//        }

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        Connection h2Conn = null;
        Statement h2Stat = null;
        ResultSet h2Res = null;

        try {

            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);

            JDBCHelperImpl helper = new JDBCHelperImpl(connection);

//          Или через MERGE: MERGE INTO tableName [ ( columnName [,...] ) ]
//          [ KEY ( columnName [,...] ) ]
//          { VALUES { ( { DEFAULT | expression } [,...] ) } [,...] | select }
//          Как лучше?

            boolean isTableExist = helper.isTableExist(MYSQL_TABLE_COLUMN_LABEL);

            if (isTableExist){
                helper.deleteRows();
            } else {
                helper.createTable();
            }

            String sqlInsert = "INSERT INTO sites VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);

            h2Conn = DriverManager.getConnection(H2_URL, H2_USER, H2_PASSWORD);
            String sqlH2 = "SELECT * FROM sites";
            h2Stat = h2Conn.createStatement();
            h2Res = h2Stat.executeQuery(sqlH2);

            while(h2Res.next()){
                preparedStatement
                        .setString(1, h2Res.getString("title"));
                preparedStatement
                        .setString(2, h2Res.getString("href"));
                preparedStatement.execute();
            }

            preparedStatement.close();
            h2Stat.close();
            h2Res.close();
            h2Conn.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (h2Stat != null) h2Stat.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (h2Res != null) h2Res.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (h2Conn != null) h2Conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
