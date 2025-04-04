package com.registro.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
// Método para buscar un rol por su nombre
Optional<Rol> findByNombre(String nombre);
}
