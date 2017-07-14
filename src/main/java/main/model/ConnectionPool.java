package main.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by video on 14.07.2017.
 */
public class ConnectionPool {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false";

    private static final ConnectionPool connectionPool = new ConnectionPool();
    private ComboPooledDataSource cp;

    private ConnectionPool() {
        try{
            Class.forName(DRIVER);
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass(DRIVER);
            cpds.setJdbcUrl(URL);
            cpds.setUser(USER);
            cpds.setPassword(PASSWORD);

            cpds.setMinPoolSize(5);
            cpds.setMaxPoolSize(20);
            cp=cpds;
        }
        catch (PropertyVetoException | ClassNotFoundException e)
        {e.printStackTrace();}

    }

    public static ConnectionPool getInstance()
    {return connectionPool;}

    public void closePool()
    {cp.close();}

    public Connection getConnection() throws SQLException
    {
        return cp.getConnection();
    }
}
