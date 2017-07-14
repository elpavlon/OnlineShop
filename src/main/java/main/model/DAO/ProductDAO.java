package main.model.DAO;

import main.model.POJO.Product;

import java.util.Collection;

/**
 * Created by video on 14.07.2017.
 */
public class ProductDAO implements DAO<Long, Product>{
    private static final String SELECT_ALL = "SELECT product_id, name, brand, cost, quantity, category_id FROM shop.product";
    private static final String INSERT_INTO = "INSERT INTO shop.product (product_id, name, brand, cost, quantity, category_id) VALUES (?, ?, ?, ?, ?, &)";
    private static final String UPDATE_WHERE = "UPDATE shop.product SET name = ?, brand = ?, cost = ?, quantity = ?, category_id = ? WHERE pruduct_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM shop.product WHERE product_id=?";

    public Collection<Product> getAll()
    {
        return null;
    }

    public Product getById(Long id)
    {
        return null;
    }

    public Long insert(Product entity)
    {
        return null;
    }

    public void update(Product entity)
    {}

    public void delete(Product entity)
    {}

}
