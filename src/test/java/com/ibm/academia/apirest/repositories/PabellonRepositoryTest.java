package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.entities.Pabellon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PabellonRepositoryTest {

    @Autowired
    private PabellonRepository pabellonRepository;

    @Test
    void findPabellonsByLocalidad() {
        String localidad = "Monterrey";
        pabellonRepository.saveAll(List.of(DatosDummy.pabellon01(),
                DatosDummy.pabellon02(),
                DatosDummy.pabellon03()));

        List<Pabellon> allByDireccion_localidad = (List<Pabellon>) pabellonRepository
                .findAllByDireccion_Localidad(localidad);

        assertThat(allByDireccion_localidad.size() == 1);
    }

}