package main.model.DAO;

import main.model.ConnectionPool;
import main.model.POJO.Product;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by video on 14.07.2017.
 */
public class ProductDAO implements DAO<Long, Product>{
    private static final String SELECT_ALL = "SELECT product_id, name, brand, cost, quantity, category_id FROM product";
    private static final String SELECT_BY_ID = "SELECT product_id, name, brand, cost, quantity, category_id FROM product WHERE product_id = ?";
    private static final String INSERT_INTO = "INSERT INTO product (name, brand, cost, quantity, category_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_WHERE = "UPDATE product SET name = ?, brand = ?, cost = ?, quantity = ?, category_id = ? WHERE pruduct_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE product_id=?";

    public Collection<Product> getAll()
    {
        Set<Product> result = new HashSet<Product>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while(resultSet.next())
            {result.add(createProduct(resultSet));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Product getById(Long id)
    {
        Product product = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = createProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public Long insert(Product entity)
    {
        Long result = -1l;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getBrand());
            statement.setFloat(3, entity.getCost());
            statement.setInt(4, entity.getQuantity());
            statement.setInt(5, entity.getCategoryId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(Product entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WHERE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getBrand());
            statement.setFloat(3, entity.getCost());
            statement.setInt(4, entity.getQuantity());
            statement.setInt(5, entity.getCategoryId());
            statement.setLong(6, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Product entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product createProduct(ResultSet resultSet) throws SQLException
    {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String brand = resultSet.getString(3);
        float cost = resultSet.getFloat(4);
        int quantity = resultSet.getInt(5);
        int  categoryId = resultSet.getInt(6);
        return new Product(id, name, brand, categoryId, cost, quantity);
    }

}
