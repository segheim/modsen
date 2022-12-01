package com.example.modsen.service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface BasicService<T> {

    Optional<T> add(@Valid T t);

    List<T> searchAll();

    Optional<T> searchById(Long id);

    Optional<T> patch(Long id, @Valid T t);

    Optional<T> delete(Long id);
}
