package jdbc;

public final class DBConstants {

    private DBConstants(){}

    static final String H2_TABLE_COLUMN_LABEL = "TABLE_NAME";
    static final String MYSQL_TABLE_COLUMN_LABEL = "Tables_in_mydb";
    public static final String TABLE_NAME = "sites";

    static final String H2_URL = "jdbc:h2:~/test";
    static final String H2_USER = "user";
    static final String H2_PASSWORD = "user";

    static final String MYSQL_URL = "jdbc:mysql://localhost:3306/mydb?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    static final String MYSQL_USER = "root";
    static final String MYSQL_PASSWORD = "English1996";

}
