package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.Servicio;



public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {

}
