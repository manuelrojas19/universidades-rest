package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.dto.CarreraDto;
import com.ibm.academia.apirest.entities.Carrera;

public class CarreraMapper {

    public static CarreraDto CarreraToCarreraDto(Carrera carrera) {
        return CarreraDto.builder()
                .id(carrera.getId())
                .nombre(carrera.getNombre())
                .cantidadAnios(carrera.getCantidadAnios())
                .cantidadMaterias(carrera.getCantidadMaterias())
                .build();
    }
}
