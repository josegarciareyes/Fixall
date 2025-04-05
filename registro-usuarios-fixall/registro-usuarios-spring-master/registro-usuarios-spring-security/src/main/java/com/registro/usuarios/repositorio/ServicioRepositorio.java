package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Servicio;


@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {

}
