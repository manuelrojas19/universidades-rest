package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;

public interface PersonaDAO extends GenericDAO<Persona> {
    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

    Persona buscarPorDni(String dni);

    Iterable<Persona> buscarPersonaPorApellido(String apellido);
}