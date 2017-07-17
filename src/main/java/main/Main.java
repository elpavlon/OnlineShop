package main;

import main.model.ConnectionPool;
import main.model.DAO.CategoryDAO;
import main.model.POJO.Category;

import java.sql.*;
import java.util.Collection;

/**
 * Created by video on 14.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        //try{
            /*ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product where product_id=1");
            resultSet.next();
            String name  = resultSet.getNString("name");
            String brand = resultSet.getString("brand");
            System.out.println(name + " " + brand);*/
            CategoryDAO categoryDAO = new CategoryDAO();
            Collection<Category> categories = categoryDAO.getAll();
        for (Category cat:
             categories) {
            System.out.println(cat.getId() + " " + cat.getName());

        }

        //}
        //catch(SQLException e)
        //{e.printStackTrace();}
    }
}
