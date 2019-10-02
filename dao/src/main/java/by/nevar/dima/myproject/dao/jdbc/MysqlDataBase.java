package by.nevar.dima.myproject.dao.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MysqlDataBase {

    public Connection connect ()throws SQLException{
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        return DriverManager.getConnection(url, user, pass);
    }

    public List<String> getTablesMetadata(DatabaseMetaData metadata) throws SQLException {
        String table[] = {"TABLE"};
        List<String> tables;
        try (ResultSet rs = metadata.getTables("myschema", null, null, table)) {
            tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        }
        return tables;
    }

    public String getColumnsMetadata(DatabaseMetaData metadata, String tableName) throws SQLException {
        StringBuilder result;
        try (ResultSet rs = metadata.getColumns("myschema", null, tableName, null)) {
            result = new StringBuilder();
            while (rs.next()) {
                result.append(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE") + " | ");
            }
        }
        return result.toString();
    }
}
