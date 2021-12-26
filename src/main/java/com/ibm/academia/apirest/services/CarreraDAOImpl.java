package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;

import java.util.List;

@Service
public class CarreraDAOImpl extends GenericDAOImpl<Carrera, CarreraRepository> implements CarreraDAO {
    private static final String NOT_FOUND_ERROR_MSG = "No se encontraron carreras";

    @Autowired
    public CarreraDAOImpl(CarreraRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrera> findByNombreContains(String nombre) {
        List<Carrera> carreras = (List<Carrera>) repository.findCarrerasByNombreContains(nombre);
        if (carreras.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return carreras;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrera> findByNombreContainsIgnoreCase(String nombre) {
        List<Carrera> carreras = (List<Carrera>) repository.findCarrerasByNombreContainsIgnoreCase(nombre);
        if (carreras.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return carreras;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrera> findByCantidadAniosAfter(Integer cantidadAnios) {
        List<Carrera> carreras = (List<Carrera>) repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
        if (carreras.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return carreras;
    }

    @Override
    public List<Carrera> findByProfesorNombreYApellido(String nombre, String apellido) {
        List<Carrera> carreras = (List<Carrera>) repository
                .buscarCarreraPorProfesorNombreYApellido(nombre, apellido);
        if (carreras.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return carreras;
    }

    @Override
    public Carrera update(Integer id, Carrera carrera) {
        Carrera carreraToUpdate = this.buscarPorId(id);
        carreraToUpdate.setId(carrera.getId());
        carreraToUpdate.setNombre(carrera.getNombre());
        carreraToUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraToUpdate.setCantidadMaterias(carrera.getCantidadMaterias());
        return repository.save(carreraToUpdate);
    }
}