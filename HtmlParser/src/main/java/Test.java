import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Test {

    public static void main(String[] args){

//        Регистрация драйвера
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.h2.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Driver driver = new org.h2.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Получаем Connection
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "user", "user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:user/user~:test");//?????
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Properties properties = new Properties();
            properties.put("user", "user");
            properties.put("password", "password");
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
