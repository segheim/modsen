package com.example.modsen.repository;

import java.util.List;
import java.util.Optional;

public interface BasicRepository<T> {

    Optional<T> create(T t);

    List<T> findAll();

    Optional<T> findById(Long id);

    void update(T t);

    void remove(T t);

}
