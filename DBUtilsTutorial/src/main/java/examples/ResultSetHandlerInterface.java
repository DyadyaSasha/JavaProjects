package examples;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.*;
import java.util.Arrays;

public class ResultSetHandlerInterface {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;

        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            QueryRunner queryRunner = new QueryRunner();
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {

                @Override
                public Object[] handle(ResultSet rs) throws SQLException {

                    if(!rs.next()){
                        return null;
                    }

                    /**
                     * {@link ResultSetMetaData} содержит информацию о свойствах и типах столбцов в {@link ResultSet}
                     */
                    ResultSetMetaData meta = rs.getMetaData();
                    int cols = meta.getColumnCount();
                    Object[] result = new Object[cols];

                    for(int i = 0; i < cols; i++){
                        /**
                         * столбцы начинаются с 1
                         */
                        result[i] = rs.getObject(i+1);
                    }
                    return result;
                }
            };

            Object[] result = queryRunner.query(connection, "SELECT * FROM employees WHERE id=?", handler, 103);
            System.out.println("Result: " + Arrays.toString(result));
        } finally {
            DbUtils.close(connection);
        }

    }
}
