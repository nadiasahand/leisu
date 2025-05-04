package com.mycompany.database.services;

import com.mycompany.database.repositories.AbstractRepository;

import java.util.List;

public abstract class AbstractService<M, C, R extends AbstractRepository<M, C>> {
    final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    public M save(M m) {
        return repository.save(m);
    }

    public M get(int id) {
        return repository.getById(id);
    }

    public List<M> getByCriteria(C criteria) {
        return repository.getByCriteria(criteria);
    }
}
