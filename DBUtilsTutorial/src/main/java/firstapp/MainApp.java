package firstapp;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainApp {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    public static void main(String[] args) throws SQLException{

        Connection connection = null;
        /**
         * класс, предоствляющий методы выполнения SQL выражений-запросов
         * (потокобезопасен)
         */
        QueryRunner queryRunner = new QueryRunner();
        /**
         * {@link DbUtils} - Потокобезопасный класс, предоставляющий методы для подключения к JDBC-драйверу, а также для работы с {@link Connection},
         * {@link java.sql.Statement}, {@link java.sql.ResultSet}
         * загружаем драйвер
         */
        DbUtils.loadDriver(JDBC_DRIVER);
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            /**
             * интерфейс {@link ResultSetHandler} предназначен для обработки и перевода {@link java.sql.ResultSet} в объект
             * в {@link BeanHandler} указываем POJO класс, в который будет преобразована строка из {@link java.sql.ResultSet}
             */
            ResultSetHandler<Employee> resultSetHandler = new BeanHandler<Employee>(Employee.class);
            /**
             * update() предназначен для INSERT, DELETE, UPDATE запросов
             */
            int insertedRecords = queryRunner.update(connection, "INSERT INTO employees(id,age,first,last) VALUES (?,?,?,?)", 104, 30, "Alex", "Serebryakov");
            System.out.println("Inserted rows: " + insertedRecords);
            /**
             * query() предназначен для SELECT запросов(в аргументах указываем connection, текст запроса, resultHandler, параметры запроса(если нужно))
             */
            Employee employee = queryRunner.query(connection, "SELECT * FROM employees WHERE first=?", resultSetHandler, "Sumit");

            System.out.print("ID: " + employee.getId());
            System.out.print(", Age: " + employee.getAge());
            System.out.print(", First: " + employee.getFirst());
            System.out.println(", Last: " + employee.getLast());

            /**
             * обработчик для нескольких строк таблицы: конвертирует {@link java.sql.ResultSet} в {@link List} Java бинов
             */
            ResultSetHandler<List<Employee>> resultListHandler = new BeanListHandler<>(Employee.class);
            for(Employee employee1 : queryRunner.query(connection, "SELECT * FROM employees", resultListHandler)){
                System.out.println(employee1.getFirst());
            }

            int deletedRows = queryRunner.update(connection, "DELETE FROM employees WHERE first=?", "Alex");
            System.out.println("Deleted rows: " + deletedRows);

        } catch (SQLException e){

        } finally {
            /**
             * закрываем {@link Connection}
             */
            DbUtils.close(connection);
        }

    }

}
