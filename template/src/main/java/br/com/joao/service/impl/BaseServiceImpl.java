package br.com.joao.service.impl;

import br.com.joao.entity.BaseEntity;
import br.com.joao.service.BaseService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<I extends Serializable, E extends BaseEntity<I>> implements BaseService<I, E> {

    @Override
    public Optional<E> findById(I id) {

        return getRepository().findById(id);
    }

    @Override
    public E save(E entity) {

        return getRepository().save(entity);
    }

    @Override
    public List<E> findAll() {

        return getRepository().findAll();
    }

    @Override
    public void delete(E midia) {

        getRepository().delete(midia);
    }

    @Override
    public void delete(I id) {

        getRepository().deleteById(id);
    }

    @Override
    public boolean existsById(I id) {

        return getRepository().existsById(id);
    }

    public static Direction getDirection(String direction) {

        try {
            return Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            return Direction.ASC;
        }
    }

    protected abstract JpaRepository<E, I> getRepository();
}
