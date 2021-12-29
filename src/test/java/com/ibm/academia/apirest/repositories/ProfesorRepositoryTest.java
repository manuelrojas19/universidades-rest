package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProfesorRepositoryTest {
    @Autowired
    @Qualifier("repositorioProfesores")
    private PersonaRepository profesorRepository;

    @Autowired
    private CarreraRepository carreraRepository;


    @Test
    void findProfesoresByCarrera() {
        Iterable<Persona> personas = profesorRepository.saveAll(
                Arrays.asList(
                        DatosDummy.profesor01(),
                        DatosDummy.profesor02(),
                        DatosDummy.profesor03())
        );
        Carrera carrera1 = carreraRepository.save(DatosDummy.carrera01());
        Carrera carrera2 = carreraRepository.save(DatosDummy.carrera02());
        personas.forEach(profesor -> ((Profesor) profesor).setCarreras(Set.of(carrera1, carrera2)));

//        profesorRepository.saveAll(personas);

        //When
        String carreraNombre = "Ingenieria en Sistemas";
        List<Persona> expected =  (List<Persona>) ((ProfesorRepository)profesorRepository).findProfesoresByCarrera(carreraNombre);

        //Then
        assertThat(expected.size() == 3).isTrue();

    }
}