package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.repositories.PersonaRepository;

public class PersonaDAOImpl extends GenericDAOImpl<Persona, PersonaRepository> implements PersonaDAO {
    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre, apellido);
    }

    @Override
    public Persona buscarPorDni(String dni) {
        return repository.buscarPorDni(dni)
                .orElseThrow(() -> new NotFoundException("No se encontró al alumno"));
    }

    @Override
    public Iterable<Persona> buscarPorApellido(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }
}