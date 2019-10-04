package by.nevar.dima.myproject.dao.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MysqlDataBaseTest {

    @Test
    void database() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        assertNotNull(connection);
    }

    @Test
    void metadata() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        DatabaseMetaData metaData = connection.getMetaData();
        assertEquals("MySQL Connector/J", metaData.getDriverName());
        assertEquals("mysql-connector-java-8.0.17 (Revision: 16a712ddb3f826a1933ab42b0039f7fb9eebc6ec)", metaData.getDriverVersion());
        assertEquals("jdbc:mysql://localhost:3306/myschema?useUnicode=true&characterEncoding=UTF-8&logger=com.mysql.cj.log.StandardLogger&profileSQL=true",
                metaData.getURL());
        assertEquals("root@localhost", metaData.getUserName());
    }

    @Test
    void tables() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        List<String> tableNames = dataBase.getTablesMetadata(connection.getMetaData());
        System.out.println(tableNames);
    }

    @Test
    void columns() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        String columnInfo = dataBase.getColumnsMetadata(connection.getMetaData(), "salary");
        System.out.println(columnInfo);
    }

    @Test
    void statement() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        Statement statement = connection.createStatement();
        try (ResultSet rs = statement.executeQuery("select * from employee")) {
            while (rs.next()) {
                System.out.println(
                        rs.getLong("id") + "|" +
                                rs.getString("name") + "|" +
                                rs.getInt("salary"));
            }
        }
    }

    @Test
    void update() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        Statement statement = connection.createStatement();
        int updatedRows = statement.executeUpdate("update employee set salary = salary + 1");
        assertTrue(updatedRows > 0);
    }

    @Test
    void rsMeta() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection connection = dataBase.connect();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from employee");
        ResultSetMetaData meta = rs.getMetaData();

        //Return the column count
        int columnCount = meta.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println("Column Name: " + meta.getColumnName(i));
            System.out.println("Column Type:" + meta.getColumnType(i));
            System.out.println("Display Size: " + meta.getColumnDisplaySize(i));
            System.out.println("Precision: " + meta.getPrecision(i));
            System.out.println("Scale: " + meta.getScale(i));
            System.out.println("___________");
        }
        System.out.println("Total row count: " + meta.getColumnCount());
    }

    @Test
    void close() throws SQLException {

        MysqlDataBase dataBase = new MysqlDataBase();
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from employee")) {
            assertNotNull(rs);
        }
    }

    private MysqlDataBase dataBase = new MysqlDataBase();

    int findByMoneyGt(int salary) throws SQLException {
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement("select salary as s from employee where salary > ?")) {
            statement.setInt(1, salary);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("s");
                }
            }
        }
        return salary;
    }

    @Test
    void preparedStatementInsert() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement("insert into employee(department_id, name, salary) values(?,?,?)")) {
            statement.setInt(1, 2);
            statement.setString(2, "dima");
            statement.setInt(3, 5000);
            statement.executeUpdate();
        }
    }

    @Test
    void preparedStatementId() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement("insert into employee(department_id, name, salary) values(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, 2);
            statement.setString(2, "dima");
            statement.setInt(3, 5000);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);

            System.out.println(id);
        }
    }


    @Test
    void noInjection() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        String name = "dima";
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement("select * from employee where name = ?")) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                            rs.getLong("id") + "|" +
                                    rs.getString("name") + "|" +
                                    rs.getInt("salary"));
                }
            }
        }
    }

    @Test
    void injection() throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        String name = "'dima'";
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from employee where name = " + name)) {
            while (rs.next()) {
                System.out.println(
                        rs.getLong("id") + "|" +
                                rs.getString("name") + "|" +
                                rs.getInt("salary"));
            }
        }
    }
}
