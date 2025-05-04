package com.mycompany.database.models.requests;

public abstract class AbstractRequest<T> {
    public abstract T toModel();
}
