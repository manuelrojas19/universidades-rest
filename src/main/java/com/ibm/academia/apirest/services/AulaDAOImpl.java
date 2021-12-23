package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaDAOImpl  extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO {

    @Autowired
    public AulaDAOImpl(AulaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Aula> findAulaByPizarron(Pizarron pizarron) {
        return repository.findAulaByPizarron(pizarron);
    }

    @Override
    public Iterable<Aula> findAulaByNombrePabellon(String pabellon) {
        return repository.findAulaByPabellon_Nombre(pabellon);
    }

    @Override
    public Aula findByNumeroAula(Integer numAula) {
        return repository.findAulaByNumeroAula(numAula).orElseThrow(() ->
                new NotFoundException("No se encontró el aula"));
    }
}
