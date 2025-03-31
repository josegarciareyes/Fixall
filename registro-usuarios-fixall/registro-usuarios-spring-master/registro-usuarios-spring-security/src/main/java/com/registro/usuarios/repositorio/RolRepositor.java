package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.Rol;

public interface RolRepositor extends JpaRepository<Rol, Long> {

}
