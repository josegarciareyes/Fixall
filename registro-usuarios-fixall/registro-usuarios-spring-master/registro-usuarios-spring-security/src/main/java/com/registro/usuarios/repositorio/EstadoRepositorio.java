package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.Estado;


public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

}
