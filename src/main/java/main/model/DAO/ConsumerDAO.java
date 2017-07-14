package main.model.DAO;

import main.model.POJO.Consumer;

import java.util.Collection;

/**
 * Created by video on 14.07.2017.
 */
public class ConsumerDAO implements DAO <Long, Consumer> {
    private static final String SELECT_ALL = "SELECT consumer_id, name, surname, tel, address FROM shop.consumer";
    private static final String INSERT_INTO = "INSERT INTO shop.consumer (name, surname, tel, address) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_WHERE = "UPDATE shop.consumer SET name = ?, surname = ?, tel = ?, address = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM shop.consumer WHERE id=?";

    public Collection<Consumer> getAll()
    {
        return null;
    }

    public Consumer getById(Long id)
    {
        return null;
    }

    public Long insert(Consumer entity)
    {
        return null;
    }

    public void update(Consumer entity)
    {}

    public void delete(Consumer entity)
    {}

}
