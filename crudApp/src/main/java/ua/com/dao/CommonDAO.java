package ua.com.dao;

import java.util.List;

public interface CommonDAO<T> {
  boolean create(T obj);
  boolean update(T obj);
  T get(long id);
  boolean delete(T obj);
  boolean delete(long id);
  List<T> getAll();
}
