package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.repositories.PabellonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PabellonDAOImpl extends GenericDAOImpl<Pabellon, PabellonRepository>
        implements PabellonDAO {

    private static final String NOT_FOUND_ERROR_MSG = "No hay pabellones registrados";

    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    public List<Pabellon> findByDireccion_Localidad(String localidad) {
        List<Pabellon> pabellones = (List<Pabellon>) repository
                .findPabellonsByDireccion_Localidad(localidad);
        if (pabellones.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return pabellones;
    }

    @Override
    public List<Pabellon> findByNombre(String nombre) {
        List<Pabellon> pabellones = (List<Pabellon>) repository.findPabellonsByNombre(nombre);
        if (pabellones.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return pabellones;
    }

    @Override
    public Pabellon actualizar(Integer id, Pabellon pabellon) {
        Pabellon pabellonToUpdate = this.buscarPorId(id);
        pabellonToUpdate.setNombre(pabellon.getNombre());
        pabellonToUpdate.setMetrosCuadrados(pabellon.getMetrosCuadrados());
        return repository.save(pabellonToUpdate);
    }
}
