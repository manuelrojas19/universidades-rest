package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class GenericDAOImpl<E, R extends CrudRepository<E, Integer>> implements GenericDAO<E> {
    protected final R repository;
    public static final String NOT_FOUND_ERROR_MSG = "No se encontraron recursos";

    public GenericDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public E buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException(NOT_FOUND_ERROR_MSG));
    }

    @Override
    @Transactional
    public E guardar(E entidad) {
        return repository.save(entidad);
    }


    @Override
    @Transactional(readOnly = true)
    public List<E> buscarTodos() {
        List<E> entitiesList = (List<E>) repository.findAll();
        if (entitiesList.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return entitiesList;
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }
}