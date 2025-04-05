# fixall

CREATE DATABASE registro_prueba_servicio;

SELECT * FROM registro_prueba_servicio.rol;
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('USUARIO');

SELECT * FROM registro_prueba_servicio.tipo_usuario;
INSERT INTO tipo_usuario (nombre) VALUES ('Cliente'),('Técnico');

SELECT * FROM registro_prueba_servicio.tipo_servicio;
INSERT INTO tipo_servicio (id, nombre) VALUES (1, 'Reparación');
INSERT INTO tipo_servicio (id, nombre) VALUES (2, 'Instalación');
INSERT INTO tipo_servicio (id, nombre) VALUES (3, 'Mantenimiento');
INSERT INTO tipo_servicio (id, nombre) VALUES (4, 'Diagnóstico');

SELECT * FROM registro_prueba_servicio.estado;
INSERT INTO estado (nombre) VALUES ('Pendiente');
INSERT INTO estado (nombre) VALUES ('En Proceso');
INSERT INTO estado (nombre) VALUES ('Completado');
INSERT INTO estado (nombre) VALUES ('Cancelado');
