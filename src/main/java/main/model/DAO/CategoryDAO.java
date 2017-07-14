package main.model.DAO;

import main.model.POJO.Category;

import java.util.Collection;

/**
 * Created by video on 14.07.2017.
 */
public class CategoryDAO implements DAO <Integer, Category> {
    private static final String SELECT_ALL = "SELECT category_id FROM shop.category";
    private static final String INSERT_INTO = "INSERT INTO shop.category name VALUES ?";
    private static final String UPDATE_WHERE = "UPDATE shop.category SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM shop.category WHERE category_id=?";

    public Collection<Category> getAll()
    {
        return null;
    }

    public Category getById(Integer id)
    {
        return null;
    }

    public Integer insert(Category entity)
    {
        return null;
    }

    public void update(Category entity)
    {}

    public void delete(Category entity)
    {}

}
