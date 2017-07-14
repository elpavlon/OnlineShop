package main.model.DAO;

import java.util.Collection;

/**
 * Created by video on 14.07.2017.
 */
public interface DAO<PK, E> {

    Collection<E> getAll();

    E getById(PK id);

    PK insert(E entity);

    void update(E entity);

    void delete(E entity);
}
