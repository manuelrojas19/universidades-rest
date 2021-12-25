package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Pabellon;

public interface PabellonDAO extends GenericDAO<Pabellon> {
    Iterable<Pabellon> findPabellonsByDireccion_Localidad(String localidad);
    Iterable<Pabellon> findPabellonsByNombre(String nombre);
}