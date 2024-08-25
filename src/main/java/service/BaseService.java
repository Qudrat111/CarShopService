package service;

import java.util.List;

public interface BaseService<T> {
    public void add(T t);
    public void update(T t);
    public void delete(T t);
    public T get(Integer id);
    public List<T> findAll();
}
