package main.model.DAO;

import main.model.POJO.Order;

import java.util.Collection;

/**
 * Created by video on 14.07.2017.
 */
public class OrderDAO implements DAO <Long, Order> {
    private static final String SELECT_ALL = "SELECT order_id, date, consumer_id, product_id, quantity FROM shop.order";
    private static final String INSERT_INTO = "INSERT INTO shop.order (date, consumer_id, product_id, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_WHERE = "UPDATE shop.order SET date = ?, consumer_id = ?, product_id = ?, quantity = ? WHERE order_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM shop.order WHERE order_id=?";

    public Collection<Order> getAll()
    {
        return null;
    }

    public Order getById(Long id)
    {
        return null;
    }

    public Long insert(Order entity)
    {
        return null;
    }

    public void update(Order entity)
    {}

    public void delete(Order entity)
    {}

}
