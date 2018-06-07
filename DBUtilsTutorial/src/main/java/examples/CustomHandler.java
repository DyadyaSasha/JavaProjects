package examples;

import firstapp.Employee;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomHandler {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    public static void main(String[] args) throws SQLException{

        Connection connection = null;

        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            QueryRunner queryRunner = new QueryRunner();
            EmployeeHandler employeeHandler = new EmployeeHandler();
            Employee employee = queryRunner.query(connection, "SELECT * FROM employees WHERE first=?", employeeHandler, "Sumit");

            System.out.print("ID: " + employee.getId());
            System.out.print(", Age: " + employee.getAge());
            System.out.print(", Name: " + employee.getName());
        } finally {
            DbUtils.close(connection);
        }
    }

}

/**
 * можно создать свой обработчик {@link java.sql.ResultSet},
 * унаследовавшись от одного из классов реализующих {@link org.apache.commons.dbutils.ResultSetHandler} интерфейс либо реализовав его напрямую
 */
class EmployeeHandler extends BeanHandler<Employee>{

    public EmployeeHandler() {
        super(Employee.class);
    }

    /**
     * переопределяем поведение обработчика
     */
    @Override
    public Employee handle(ResultSet resultSet) throws SQLException{
        Employee employee = super.handle(resultSet);
        employee.setName(employee.getFirst() + ", " + employee.getLast());
        return employee;
    }
}