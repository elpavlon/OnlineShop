package main.model;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by video on 14.07.2017.
 */
public class ConnectionPool {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";

    private static final ConnectionPool connectionPool = new ConnectionPool();
    private BoneCP cp;

    private ConnectionPool() {
        try{
            Class.forName(DRIVER);
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USER);
            config.setPassword(PASSWORD);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            cp = new BoneCP(config);
        }
        catch (SQLException | ClassNotFoundException e)
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
