package utils;

import data.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface JDBCHelper {

    void createTable() throws SQLException;

    void createTableV2() throws SQLException;

    void deleteRows() throws  SQLException;

    boolean isTableExist(String tabelLabel) throws SQLException;

    void inMemoryInsert(List<DataSource> ds) throws SQLException;

    void inPersistentInsert(Connection h2Conn) throws SQLException;
}
