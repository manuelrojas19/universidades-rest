package com.ibm.academia.apirest.services;

import java.util.List;

public interface GenericDAO<E> {
    E buscarPorId(Integer id);

    E guardar(E entidad);

    List<E> buscarTodos();

    void eliminarPorId(Integer id);
}