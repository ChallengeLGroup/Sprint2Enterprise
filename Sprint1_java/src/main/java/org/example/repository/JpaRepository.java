package org.example.repository;

import jakarta.persistence.EntityManager;

import java.util.List;

public class JpaRepository<T> implements Repository<T>{

    protected EntityManager entityManager;
    private Class<T> entityType;

    public JpaRepository(EntityManager entityManager, Class<T> entityType) {
        this.entityManager = entityManager;
        this.entityType = entityType;
    }
    @Override
    public List<T> getAll() {
        var jpql = "SELECT u FROM " + entityType.getSimpleName() + " u";
        var query = entityManager.createQuery(jpql, entityType);
        return query.getResultList();
    }

    @Override
    public T findById(Object id) {

        return entityManager.find(entityType, id);
    }

    @Override
    public void create(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public void update(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public void deleteById(Object id) {
        var entity = findById(id);
        if(entity != null)
        {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
            }
            catch (Exception e){
                entityManager.getTransaction().rollback();
                throw e;
            }
        }

    }
}
