package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Pabellon;

import java.util.List;

public interface PabellonDAO extends GenericDAO<Pabellon> {
    List<Pabellon> findByLocalidad(String localidad);
    List<Pabellon> findByNombre(String nombre);
    Pabellon actualizar(Integer id, Pabellon profesor);
}
