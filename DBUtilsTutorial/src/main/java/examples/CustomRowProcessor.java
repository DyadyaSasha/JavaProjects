package examples;

import firstapp.Employee;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomRowProcessor {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "user";

    public static void main(String[] args) throws SQLException{

        Connection connection = null;

        try {
            DbUtils.loadDriver(JDBC_DRIVER);
            QueryRunner queryRunner = new QueryRunner();
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Employee employee = queryRunner.query(connection, "SELECT * FROM employees WHERE first=?", new EmployeeHandler(), "Sumit");

            System.out.print("ID: " + employee.getId());
            System.out.print(", Age: " + employee.getAge());
            System.out.print(", Name: " + employee.getName());

        } finally {
            DbUtils.close(connection);
        }


    }
}

class EmployeeHandlerForRow extends BeanHandler<Employee>{

    /**
     * указываем обработчик для строк таблицы, указываем словарь(Map), в котором указан свой маппинг
     * имён полей сущности с именами столбцов из {@link ResultSet}
     */
    public EmployeeHandlerForRow(){
        super(Employee.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    @Override
    public Employee handle(ResultSet rs) throws SQLException{
        Employee employee = super.handle(rs);
        employee.setName(employee.getFirst() + ", " + employee.getLast());
        return employee;
    }

    public static Map<String, String> mapColumnsToFields(){
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("ID", "id");
        columnsToFieldsMap.put("AGE", "age");
        return columnsToFieldsMap;
    }
}
