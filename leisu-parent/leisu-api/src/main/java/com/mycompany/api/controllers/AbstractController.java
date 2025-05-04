package com.mycompany.api.controllers;


import com.mycompany.database.repositories.AbstractRepository;
import com.mycompany.database.services.AbstractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractController<T, C, R extends AbstractRepository<T, C>, S extends AbstractService<T, C, R>> {

    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public T getById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("filter")
    public List<T> getById(@ModelAttribute C criteria) {
        return service.getByCriteria(criteria);
    }
}
