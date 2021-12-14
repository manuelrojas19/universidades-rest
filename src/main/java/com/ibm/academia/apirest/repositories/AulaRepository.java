package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer> {
    Iterable<Aula> findAulaByPizarron(Pizarron pizarron);

    Iterable<Aula> findAulaByPabellon_Nombre(String nombrePabellon);

    Optional<Aula> findAulaByNumeroAula(Integer numeroAula);
}
