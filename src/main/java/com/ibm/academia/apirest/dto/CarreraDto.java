package com.ibm.academia.apirest.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarreraDto {
    private Integer id;
    @NotNull(message = "No puede ser nulo")
    @NotEmpty
    @Size(min=5, max = 80)
    private String nombre;
    @Positive(message = "El numero debe ser mayor a 0")
    private Integer cantidadMaterias;
    @Positive(message = "El numero debe ser mayor a 0")
    private Integer cantidadAnios;
}
