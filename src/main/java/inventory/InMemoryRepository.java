package inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<ID, T> implements Repository<ID, T>{
    private final Map<ID, T> repo = new HashMap<>();

    @Override
    public void save(ID id, T entity) {
        repo.put(id, entity);
    }

    @Override
    public T findById(ID id) {
        return repo.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public void deleteById(ID id) {
        repo.remove(id);
    }
}
