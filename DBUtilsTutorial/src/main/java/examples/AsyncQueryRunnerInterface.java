package examples;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;

public class AsyncQueryRunnerInterface {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    public static void main(String[] args) throws SQLException, InterruptedException, ExecutionException, TimeoutException {

        Connection connection = null;

        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            /**
             * {@link AsyncQueryRunner} предстовляет методы аналогично {@link org.apache.commons.dbutils.QueryRunner}, но в асинхронном режиме
             * при создании объекта передаём пул потоков
             */
            AsyncQueryRunner asyncQueryRunner = new AsyncQueryRunner(Executors.newCachedThreadPool());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            /**
             * {@link Future} представляет результат асинхронного вызова
             */
            Future<Integer> future = null;
            /**
             * выполняем в асинхронном режиме
             */
            future = asyncQueryRunner.update(connection, "UPDATE employees SET age=? WHERE id=?", 33,103);
            /**
             * get() - ждём 10 секунд, если ответа нет - {@link TimeoutException}
             */
            Integer updateRecords = future.get(10, TimeUnit.SECONDS);
            System.out.println("Updated records: " + updateRecords);
        } finally {
            DbUtils.close(connection);
        }


    }
}
