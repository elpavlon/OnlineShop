package main;

import main.model.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by video on 14.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        try{
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection connection = cp.getConnection();
            
        }
        catch(SQLException e)
        {e.printStackTrace();}
    }
}
