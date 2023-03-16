package uz.test.dao;

import uz.test.entity.AbsEntity;

import java.sql.Connection;
import java.util.List;

public abstract class BaseDao<T extends AbsEntity>{
    protected Connection connection;
    abstract public boolean create(T t);
    abstract public boolean delete(int id);
    abstract public List<T> findAll();
    abstract public T findById(int id);
    abstract public boolean update(T t);
}
