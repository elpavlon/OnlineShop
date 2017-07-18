package main.model.DAO;

import main.model.ConnectionPool;
import main.model.POJO.Order;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by video on 14.07.2017.
 */
public class OrderDAO implements DAO <Long, Order> {
    private static final String SELECT_ALL = "SELECT order_id, date, consumer_id, product_id, quantity FROM order";
    private static final String SELECT_BY_ID = "SELECT order_id, date, consumer_id, product_id, quantity FROM order WHERE order_id = ?";
    private static final String INSERT_INTO = "INSERT INTO order (date, consumer_id, product_id, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_WHERE = "UPDATE order SET date = ?, consumer_id = ?, product_id = ?, quantity = ? WHERE order_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM order WHERE order_id = ?";

    public Collection<Order> getAll()
    {
        Set<Order> result = new HashSet<Order>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while(resultSet.next())
            {result.add(createOrder(resultSet));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Order getById(Long id)
    {
        Order order = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = createOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public Long insert(Order entity)
    {
        Long result = -1l;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS)) {

            statement.setTimestamp(1, entity.getDate());
            statement.setLong(2, entity.getConsumerId());
            statement.setLong(3, entity.getProductId());
            statement.setInt(4, entity.getQuantity());
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

    public void update(Order entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WHERE)) {
            statement.setTimestamp(1, entity.getDate());
            statement.setLong(2, entity.getConsumerId());
            statement.setLong(3, entity.getProductId());
            statement.setInt(4, entity.getQuantity());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Order entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order createOrder(ResultSet resultSet) throws SQLException
    {
        long id = resultSet.getLong(1);
        Timestamp date = resultSet.getTimestamp(2);
        long consumerId = resultSet.getLong(3);
        long productId = resultSet.getLong(4);
        int quantity = resultSet.getInt(5);

        return new Order(id, consumerId, productId, date, quantity);
    }

}
