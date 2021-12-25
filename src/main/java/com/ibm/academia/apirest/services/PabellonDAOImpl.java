package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PabellonDAOImpl extends GenericDAOImpl<Pabellon, PabellonRepository>
        implements PabellonDAO{

    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Pabellon> findPabellonsByDireccion_Localidad(String localidad) {
        return repository.findPabellonsByDireccion_Localidad(localidad);
    }

    @Override
    public Iterable<Pabellon> findPabellonsByNombre(String nombre) {
        return repository.findPabellonsByNombre(nombre);
    }
}
