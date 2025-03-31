package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.TipoServicio;

public interface TipoServicioRepositorio extends JpaRepository<TipoServicio, Long> {

}
