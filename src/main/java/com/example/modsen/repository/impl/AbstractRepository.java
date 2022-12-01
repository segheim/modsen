package com.example.modsen.repository.impl;

import com.example.modsen.repository.BasicRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public abstract class AbstractRepository<T> implements BasicRepository<T> {

    private final Class<T> entityType;

    @PersistenceContext
    protected EntityManager entityManager;

    protected AbstractRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public Optional<T> create(T t) {
        try {
            entityManager.persist(t);
            return Optional.of(t);
        } catch (PersistenceException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        final TypedQuery<T> query = entityManager.createQuery("select e from " + entityType.getSimpleName()
                + " e", entityType);
        return query.getResultList();
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public void remove(T t) {
        entityManager.remove(t);
    }
}
