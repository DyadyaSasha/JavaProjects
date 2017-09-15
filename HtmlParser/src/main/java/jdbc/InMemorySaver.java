package jdbc;

import data.DataSource;
import org.h2.Driver;
import org.h2.jdbc.JdbcSQLException;
import utils.JDBCHelperImpl;

import java.sql.*;
import java.util.List;

import static jdbc.DBConstants.*;

public class InMemorySaver {

    public void save(List<DataSource> ds){


//        try {
//            Class.forName("org.h2.Driver").newInstance(); // в новых версиях JDBC  можно не подгружать(регистрировать) класс JDBC-драйвера
//        } catch (ClassNotFoundException | InstantiationException
//                | IllegalAccessException e) {
//            e.printStackTrace();
//        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

//          TODO: попробовать взять connection напрямую с помощью класса Driver

            connection = DriverManager.getConnection(H2_URL, H2_USER, H2_PASSWORD);

            JDBCHelperImpl helper = new JDBCHelperImpl(connection);
            

//          Или через MERGE: MERGE INTO tableName [ ( columnName [,...] ) ]
//          [ KEY ( columnName [,...] ) ]
//          { VALUES { ( { DEFAULT | expression } [,...] ) } [,...] | select }
//          Как лучше?

            boolean isTableExist = helper.isTableExist(H2_TABLE_COLUMN_LABEL);


            if (isTableExist){
                helper.deleteRows();
            } else {
                helper.createTable();
            }

            String sqlInsert = "INSERT INTO sites VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);

            for(DataSource site : ds) {
                preparedStatement.setString(1, site.getTitle());
                preparedStatement.setString(2, site.getHref());
                preparedStatement.execute();
            }



//            helper.createTableV2();
//            helper.inMemoryInsert(ds);

            preparedStatement.close();
            connection.close();

        } catch (JdbcSQLException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
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
