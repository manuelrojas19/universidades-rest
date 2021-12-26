package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaDAOImpl  extends GenericDAOImpl<Aula, AulaRepository> implements AulaDAO {

    @Autowired
    public AulaDAOImpl(AulaRepository repository) {
        super(repository);
    }

    @Override
    public List<Aula> findByPizarron(Pizarron pizarron) {
        return (List<Aula>) repository.findAulaByPizarron(pizarron);
    }

    @Override
    public List<Aula> findByNombrePabellon(String pabellon) {
        return (List<Aula>) repository.findAulaByPabellon_Nombre(pabellon);
    }

    @Override
    public Aula findByNumeroAula(Integer numAula) {
        return repository.findAulaByNumeroAula(numAula).orElseThrow(() ->
                new NotFoundException("No se encontró el aula"));
    }

    @Override
    public Aula actualizar(Integer id, Aula aula) {
        Aula aulaToUpdate = this.buscarPorId(id);

        aulaToUpdate.setNumeroAula(aula.getNumeroAula());
        aulaToUpdate.setCantidadPupitres(aula.getCantidadPupitres());
        aulaToUpdate.setMedidas(aula.getMedidas());
        aulaToUpdate.setPizarron(aula.getPizarron());

        return repository.save(aula);
    }
}
