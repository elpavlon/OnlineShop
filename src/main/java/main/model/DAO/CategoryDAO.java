package main.model.DAO;

import main.model.ConnectionPool;
import main.model.POJO.Category;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by video on 14.07.2017.
 */
public class CategoryDAO implements DAO <Integer, Category> {
    private static final String SELECT_ALL = "SELECT category_id, name FROM category";
    private static final String SELECT_BY_ID = "SELECT category_id, name FROM category WHERE category_id = ?";
    private static final String INSERT_INTO = "INSERT INTO category (name) VALUES (?)";
    private static final String UPDATE_WHERE = "UPDATE category SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM category WHERE category_id=?";

    public Collection<Category> getAll()
    {
        Set<Category> result = new HashSet<Category>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement())
            {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while(resultSet.next())
            {result.add(createCategory(resultSet));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Category getById(Integer id)
    {
        Category category = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = createCategory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public Integer insert(Category entity)
    {
        Integer result = -1;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(Category entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WHERE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Category entity)
    {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        return new Category(id, name);
    }

}
