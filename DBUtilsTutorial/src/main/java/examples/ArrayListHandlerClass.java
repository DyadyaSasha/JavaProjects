package examples;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArrayListHandlerClass {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            QueryRunner queryRunner = new QueryRunner();
            /**
             * конвертирует {@link java.sql.ResultSet} в лист({@link List}), элементы которого представляют собой Object[](массив объектов)
             */
            ArrayListHandler arrayListHandler = new ArrayListHandler();
            List<Object[]> result = queryRunner.query(connection, "SELECT * FROM employees", arrayListHandler);
            for (Object[] employee : result) {
                System.out.println(Arrays.toString(employee));
            }
            System.out.println("****");
            /**
             * {@link MapListHandler} конвертирует {@link java.sql.ResultSet} в лист({@link List}) словарей({@link Map})
             */
            List<Map<String, Object>> employeeMap = queryRunner.query(connection, "SELECT * FROM employees", new MapListHandler());
            /**
             * будет печататься имя столбца и его значение
             */
            for (Map<String, Object> map : employeeMap) {
                System.out.println(map.toString());
            }
            System.out.println("****");
            /**
             * передаём объект {@link DataSource} - теперь нет необходимости создавать {@link Connection} вручную
             */
            QueryRunner queryRunner1 = new QueryRunner(CustomDataSource.getInstance());
            employeeMap = queryRunner1.query("SELECT * FROM employees", new MapListHandler());
            for (Map<String, Object> map : employeeMap) {
                System.out.println(map.toString());
            }
            System.out.println("****");
        } finally {
            DbUtils.close(connection);
        }

    }

}

/**
 * объект {@link DataSource } хранит в себе данные подключения к БД
 */
class CustomDataSource{

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    private static final BasicDataSource basicDataSource;

    static {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(JDBC_DRIVER);
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
    }

    public static DataSource getInstance(){
        return basicDataSource;
    }
}
