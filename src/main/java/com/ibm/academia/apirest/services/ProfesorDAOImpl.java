package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
    private static final String NOT_FOUND_ERROR_MSG = "No se encontraron profesores";

    @Autowired
    public ProfesorDAOImpl(@Qualifier("repositorioProfesores") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarProfesorPorCarrera(String nombreCarrera) {
        List<Persona> profesores = (List<Persona>) ((ProfesorRepository) repository)
                .findProfesoresByCarrera(nombreCarrera);
        if (profesores.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return profesores;
    }

    @Override
    @Transactional
    public Profesor actualizar(Integer id, Profesor profesor) {
        Profesor profesorToUpdate = (Profesor) this.buscarPorId(id);
        profesorToUpdate.setNombre(profesor.getNombre());
        profesorToUpdate.setApellido(profesor.getApellido());
        profesorToUpdate.setDireccion(profesor.getDireccion());
        profesorToUpdate.setSueldo(profesor.getSueldo());
        profesorToUpdate.setDni(profesor.getDni());
        return repository.save(profesorToUpdate);
    }
}
