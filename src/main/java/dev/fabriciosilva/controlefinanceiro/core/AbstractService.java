package dev.fabriciosilva.controlefinanceiro.core;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T, ID> {

    /**
     * @Return the entity on database or null
     *
     */
    public T getById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public void delete(T entity) {
        getRepository().delete(entity);
    }

    public abstract JpaRepository<T, ID> getRepository();
}
