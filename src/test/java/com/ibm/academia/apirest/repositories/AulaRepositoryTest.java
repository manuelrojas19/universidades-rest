package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class AulaRepositoryTest {

    @Autowired
    private AulaRepository aulaRepository;

    @Test
    void findAulaByPizarron() {
        aulaRepository.saveAll(List.of(DatosDummy.aula01(), DatosDummy.aula02(), DatosDummy.aula03()));
        Pizarron pizarron = Pizarron.PIZARRA_BLANCA;

        List<Aula> expected = (List<Aula>) aulaRepository.findAulaByPizarron(pizarron);

        expected.forEach(aula -> assertThat(aula.getPizarron().equals(pizarron)));
    }

    @Test
    void findAulaByPabellon_Nombre() {
        DatosDummy.aula01().setPabellon(DatosDummy.pabellon01());
        DatosDummy.aula02().setPabellon(DatosDummy.pabellon02());
        DatosDummy.aula03().setPabellon(DatosDummy.pabellon03());

        aulaRepository.saveAll(List.of(DatosDummy.aula01(), DatosDummy.aula02(), DatosDummy.aula03()));
        String nombre = "Ciencias";

        Iterable<Aula> aulas = aulaRepository.findAulaByPabellon_Nombre(nombre);

        aulas.forEach(aula -> assertThat(aula.getPabellon().getNombre().equals(nombre)));
    }


}