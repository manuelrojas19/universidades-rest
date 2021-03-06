INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (1, 'Manuel', 'Rojas', '2019600987', 'Sur 161', '2436', '08000', '34', '1', 'Iztacalco', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (2, 'Luis', 'Galan', '2019609387', 'Sur 121', '2426', '07000', '32', '3', 'Azcapotzalco', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (3, 'Victor', 'Arias', '2016080987', 'Av. Té 12', '436', '12000', '11', '1', 'Iztacalco', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (4, 'Susana', 'Cuevas', '2066009787', 'Miramontes', '124', '05000', '32', '2', 'Roma Norte', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (5, 'Erick', 'Huerta', '2011609867', 'Edimburgo', '2346', '09000', '31', '1', 'Condesa', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (6, 'Hermes', 'Casiano', '2016620087', 'Amsterdam', '6436', '08010', '32', '1', 'Condesa', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (7, 'Luis', 'Jimenez', '2011600987', 'Sur 166', '236', '08000', '34', '1', 'Iztacalco', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (8, 'Gustavo', 'Ferreira', '2016450987', 'Napoles', '36', '08080', '34', '1', 'Santa Fé', CURRENT_DATE, CURRENT_DATE);
INSERT INTO personas (id, nombre, apellido, dni, calle, numero, codigo_postal, departamento, piso, localidad, fecha_alta, fecha_modificacion) VALUES (9, 'Sebastian', 'Perez', '2012005987', 'Presidente Masaryk', '26', '08010', '34', '1', 'Polanco', CURRENT_DATE, CURRENT_DATE);

INSERT INTO carreras (id, nombre, cantidad_anios, cantidad_materias, fecha_alta, fecha_modificacion) VALUES (1, 'Lic Ciencias de la Informatica', 4, 72, CURRENT_DATE , CURRENT_DATE );
INSERT INTO carreras (id, nombre, cantidad_anios, cantidad_materias, fecha_alta, fecha_modificacion) VALUES (2, 'Ing Informatica', 4, 72, CURRENT_DATE , CURRENT_DATE );
INSERT INTO carreras (id, nombre, cantidad_anios, cantidad_materias, fecha_alta, fecha_modificacion) VALUES (3, 'Ing Industrial', 4, 72, CURRENT_DATE , CURRENT_DATE );

INSERT INTO alumnos (persona_id, carrera_id) VALUES (1, 1);
INSERT INTO alumnos (persona_id, carrera_id) VALUES (2, 2);
INSERT INTO alumnos (persona_id, carrera_id) VALUES (3, 3);

INSERT INTO profesores (persona_id, sueldo) VALUES (4, 32000);
INSERT INTO profesores (persona_id, sueldo) VALUES (5, 25000);
INSERT INTO profesores (persona_id, sueldo) VALUES (6, 28000);

INSERT INTO profesor_carrera (profesor_id, carrera_id) VALUES (4, 1);
INSERT INTO profesor_carrera (profesor_id, carrera_id) VALUES (4, 2);
INSERT INTO profesor_carrera (profesor_id, carrera_id) VALUES (5, 1)
INSERT INTO profesor_carrera (profesor_id, carrera_id) VALUES (5, 2)
INSERT INTO profesor_carrera (profesor_id, carrera_id) VALUES (6, 2)
INSERT INTO profesor_carrera (profesor_id, carrera_id) VALUES (6, 3)

INSERT INTO pabellones (id, nombre, calle, numero, departamento, piso, localidad, codigo_postal, metros_cuadrados, fecha_alta, fecha_modificacion) VALUES (1, 'Computacion', 'Sur 161', '11', '12', '2', 'Iztacalco', '08000', 100, CURRENT_DATE, CURRENT_DATE );
INSERT INTO pabellones (id, nombre, calle, numero, departamento, piso, localidad, codigo_postal, metros_cuadrados, fecha_alta, fecha_modificacion) VALUES (2, 'Diseño', 'Sur 161', '11', '12', '1', 'Benito Juarez', '08000', 100, CURRENT_DATE, CURRENT_DATE );
INSERT INTO pabellones (id, nombre, calle, numero, departamento, piso, localidad, codigo_postal, metros_cuadrados, fecha_alta, fecha_modificacion) VALUES (3, 'Redes', 'Sur 161', '11', '12', '3', 'Iztacalco', '08000', 100, CURRENT_DATE, CURRENT_DATE );

INSERT INTO empleados (persona_id, sueldo, tipo_empleado, pabellon_id) VALUES (7, 15000, 'ADMINISTRATIVO', 1);
INSERT INTO empleados (persona_id, sueldo, tipo_empleado, pabellon_id) VALUES (8, 10000, 'MANTENIMIENTO', 2);
INSERT INTO empleados (persona_id, sueldo, tipo_empleado, pabellon_id) VALUES (9, 15000, 'ADMINISTRATIVO', 3);

INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (1, 11, 'PIZARRA_BLANCA', 30, '30m x 40m', 1, CURRENT_DATE , CURRENT_DATE );
INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (2, 12, 'PIZARRA_TIZA', 30, '30m x 40m', 1, CURRENT_DATE , CURRENT_DATE );
INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (3, 13, 'PIZARRA_BLANCA', 30, '30m x 40m', 1, CURRENT_DATE , CURRENT_DATE );
INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (4, 14, 'PIZARRA_TIZA', 30, '30m x 40m', 2, CURRENT_DATE , CURRENT_DATE );
INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (5, 15, 'PIZARRA_BLANCA', 30, '30m x 40m', 2, CURRENT_DATE , CURRENT_DATE );
INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (6, 16, 'PIZARRA_BLANCA', 30, '30m x 40m', 3, CURRENT_DATE , CURRENT_DATE );
INSERT INTO aulas (id, numero_aula, tipo_pizarron, cantidad_pupitres, medidas, pabellon_id, fecha_alta, fecha_modificacion) VALUES (7, 17, 'PIZARRA_TIZA', 30, '30m x 40m', 3, CURRENT_DATE , CURRENT_DATE );