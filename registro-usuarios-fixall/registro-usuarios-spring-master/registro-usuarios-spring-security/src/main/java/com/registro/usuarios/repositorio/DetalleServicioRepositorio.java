package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.DetalleServicio;



public interface DetalleServicioRepositorio extends JpaRepository<DetalleServicio, Long> {

}
