package inventory;

import java.util.List;

public interface Repository<ID, T> {
    void save(ID id, T entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}
