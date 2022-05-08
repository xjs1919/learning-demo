package com.example.demo.mock;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 11:25
 */
public class RepositoryProxy implements CrudRepository {

    private EntityManager entityManager;
    Class entityClass;

    public RepositoryProxy(EntityManagerFactory entityManagerFactory,
                           Class entityClass){
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityClass = entityClass;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.of(entityManager.find(entityClass, o));
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public Iterable saveAll(Iterable entities) {
        return null;
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAllById(Iterable iterable) {

    }

    @Override
    public void deleteAll(Iterable entities) {

    }

    @Override
    public void deleteAll() {

    }
}
