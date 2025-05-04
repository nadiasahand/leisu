package com.mycompany.database.repositories;

import java.util.List;

public interface AbstractRepository<T, C> {
    T getById(long id);

    T save(T t);

    T update(T t);

    T delete(int id);

    List<T> getByCriteria(C criteria);
}
