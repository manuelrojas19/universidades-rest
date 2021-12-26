package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Profesor;

import java.util.List;

public interface PabellonDAO extends GenericDAO<Pabellon> {
    List<Pabellon> findByDireccion_Localidad(String localidad);
    List<Pabellon> findByNombre(String nombre);

    Pabellon actualizar(Integer id, Pabellon profesor);
}
