package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;

import java.util.List;

public interface CarreraDAO extends GenericDAO<Carrera> {
    List<Carrera> findByNombreContains(String nombre);
    List<Carrera> findByNombreContainsIgnoreCase(String nombre);
    List<Carrera> findByCantidadAniosAfter(Integer cantidadAnios);
    List<Carrera> findByProfesorNombreYApellido(String nombre, String apellido);
    Carrera update(Integer id, Carrera carrera);
}