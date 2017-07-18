package main.model.DAO;

import main.model.ConnectionPool;
import main.model.POJO.Consumer;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by video on 14.07.2017.
 */
public class ConsumerDAO implements DAO <Long, Consumer> {
    private static final String SELECT_ALL = "SELECT consumer_id, name, surname, tel, address FROM consumer";
    private static final String SELECT_BY_ID = "SELECT consumer_id, name, surname, tel, address FROM consumer WHERE consumer_id = ?";
    private static final String INSERT_INTO = "INSERT INTO consumer (name, surname, tel, address) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_WHERE = "UPDATE consumer SET name = ?, surname = ?, tel = ?, address = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM consumer WHERE id=?";

    public Collection<Consumer> getAll()
    {
        Set<Consumer> result = new HashSet<Consumer>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while(resultSet.next())
            {result.add(createConsumer(resultSet));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Consumer getById(Long id)
    {
        Consumer consumer = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                consumer = createConsumer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consumer;
    }

    public Long insert(Consumer entity)
    {
        Long result = -1l;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getTel());
            statement.setString(4, entity.getAddress());
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

    public void update(Consumer entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WHERE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getTel());
            statement.setString(4, entity.getAddress());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Consumer entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Consumer createConsumer(ResultSet resultSet) throws SQLException
    {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        String tel = resultSet.getString(4);
        String address = resultSet.getString(5);
        return new Consumer(id, name, surname, tel, address);
    }

}
