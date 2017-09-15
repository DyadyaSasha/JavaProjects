import org.h2.Driver;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        try {
            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "user", "user");
            Statement st = null;
            st = conn.createStatement();
            String sql = "CREATE TABLE sites " +
                    "(title VARCHAR(255) not NULL, " +
                    "href VARCHAR(255) not NULL)";
            st.executeUpdate(sql);
//            while (rs.next()){
//                System.out.println("id: " + rs.getInt("id") +
//                        " age: " + rs.getInt("age") + " first: " +
//                        rs.getString("first") +
//                        " last: " + rs.getString("last"));
//            }
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
}
