package com.example.ticket_management.service.common;

public interface IGenerationService<T> {
    Iterable<T> findAll();
    T save(T t);
    T findById(Integer id);
    void deleteById(Integer id);
}
