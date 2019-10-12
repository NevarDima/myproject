package by.nevar.dima.myproject.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {

    private final ComboPooledDataSource pool;

    public DataSource() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        pool.setJdbcUrl(url);
        try {
            pool.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            throw new RuntimeException();
        }
        pool.setUser(user);
        pool.setPassword(pass);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(180);

    }

    private static class SingletonHolder {
        static final DataSource HOLDER_INSTANCE = new DataSource();
    }

    public static DataSource getInstance() {
        return DataSource.SingletonHolder.HOLDER_INSTANCE;
    }

    public Connection getConnection() {
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
