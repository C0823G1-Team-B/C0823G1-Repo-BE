package com.example.ticket_management.service.common;

import java.util.Optional;

public interface IGenerationService<T> {
    Iterable<T> findAll();
    T save(T t);
    Optional<T> findById(Integer id);
    void deleteById(Integer id);
}
